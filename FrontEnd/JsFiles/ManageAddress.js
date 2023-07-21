let addresslist_append=document.querySelector("#AddressList");

let customer= JSON.parse(localStorage.getItem("token"))|| [];

let addressListData= JSON.parse(localStorage.getItem("addressListData")) || [];

async function addressList(){
    if(customer==null){
        window.location.href="/FrontEnd\signUp.html";
    }else{
        try {
            let res= await fetch ('http://localhost:8088/GetAddressList/'+customer.userId);// this api call the all the address by customer id;
            let data= await res.json();
            //console.log(data);
            if(data!=null){
                localStorage.removeItem("addressListData");
                localStorage.setItem("addressListData",JSON.stringify(data));
            }
            
        } catch (error) {
            console.log(error)
        }
    }

}

async function postAddress(data){
    if(customer==null){
        window.location.href="/FrontEnd\signUp.html";
    }
    else{
        try {        
            let res= await fetch("http://localhost:8088/Add/Address/"+customer.userId,{// this api post the address
                method:'POST',
                headers:{
                    "Content-Type":"application/json"
                },
                body:JSON.stringify(data)
            });

            let apiData= await res.json();
            if(apiData!==null){
                addressListData.push(apiData);
                //console.log(addressListData)
                localStorage.setItem("addressListData",JSON.stringify(addressListData));
                
            }

        } catch (error) {
            console.log(error);
        }
    }
}

async function EditAddress(data, index) {
    try {
        let res= await fetch("http://localhost:8088/UpdateAddress",{// this api post the address
            method: 'PUT',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        });

        let apiData= await res.json();
        if(apiData !==null){
            //console.log(apiData);
            removeDataToJson(index);
            addressListData.push(apiData);
            //console.log(addressListData);
            localStorage.setItem("addressListData",JSON.stringify(addressListData));
        }

    } catch (error) {
        console.log(error)
    }
    
}

async function DeleteAddress(addressId, index){ // this api will delete the selected address on addresslist also delete form local host to;
    try {
        let res= await fetch(`http://localhost:8088/Delete/${addressId}`,{
            method: 'DELETE',
            headers:{
                "Content-Type":"application/json"
            }
        });
        let data= await res.json();
        if(data !== null){
            removeDataToJson(index);
            //location.reload();
        }
    } catch (error) {
        console.log(error)
    }
}


//****************************************************************************************************** This is a Normal  JS  *********************************************************************/

function Addresses(addresses){ // this method create addreses list tables 
    //console.log(addresses);
    addresses.forEach((i, index) =>{

        let addressDivBlock= document.createElement("div");// this div hold the address block;
        addressDivBlock.setAttribute("class","addressBolckManager");

        let div_but= document.createElement("div");

        let button= document.createElement("button");
        button.innerText= i.addressType;

        let dots= document.createElement("button");
        dots.innerHTML= `<i class="fa-solid fa-ellipsis-vertical"></i>`;
        dots.style.fontSize= "16px";

    //**********************************************this section will help to edit or delete addresses for front end side*************************************************************************\\

        let popDots= document.createElement("div");
        popDots.setAttribute("class", "addressSetting");

        let popDeleteEdit= document.createElement("div");
        popDeleteEdit.setAttribute("class", "addressSettingDrop");

        popDots.append(dots, popDeleteEdit);

        let editPop= document.createElement("p");
        editPop.innerText= "Edit";
        editPop.addEventListener("click", function(){
            let form= document.querySelector("#addressFrom")

            form.style.display="block";
            form.style.marginTop= "2%"
            let edit_div_hide=document.querySelectorAll(".addressBolckManager")
            edit_div_hide[index].style.display= "none"; // this will hide the current edit block

            // here we puting value in form to edit 

            form.name.value= i.aname;
            form.phone.value= i.aphoneNum;
            form.pincode.value= i.pinCode;
            form.locality.value= i.locality;
            form.areaAndStreet.value= i.streetAddress;
            form.city.value= i.city;
            form.state.value= i.state;
            form.landmark.value= i.landmark;
            form.alternatePhone.value= i.aphoneNumAlternative;
            form.locationTypeTag.value= i.addressType;

            document.querySelector("#titalAddress").innerText= "EDIT ADDRESS";
            document.querySelector("#button-maSave").onclick=(event)=>{//  this call back function help to the update API to do process  I puted call back function in " save button" ;
                let obj= {
                    "addressId": i.addressId,
                    "aname": form.name.value,
                    "aphoneNum": form.phone.value,
                    "pinCode": form.pincode.value,
                    "locality": form.locality.value,
                    "streetAddress": form.areaAndStreet.value,
                    "city": form.city.value,
                    "state": form.state.value,
                    "country": "India",
                    "landmark": form.landmark.value,
                    "aphoneNumAlternative": form.alternatePhone.value,
                    "addressType": form.locationTypeTag.value, 
                }
                //event.preventDefault();
                EditAddress(obj, index); // there we pass 2 value one is object (for updating) and second is index ( for remove old addresss  by passing index ) this is for current address list index
            }

        });

        let deletePop= document.createElement("p");
        deletePop.innerText= "Delete";
        deletePop.onclick=(event)=>{// this help to delete the address
             DeleteAddress(i.addressId, index);
        }; 

        popDeleteEdit.append(editPop, deletePop);


    //888888888888888888888888888888888888888888888888    END This Section    888888888888888888888888888888888888888888888888888888888888888888\\

        div_but.append(button, popDots);
        div_but.setAttribute("class", "diff_but_dot");

        let ntag= document.createElement("p");
        ntag.innerText= i.aname +"        "+ i.aphoneNum;
        ntag.style.fontWeight="600";

        let dtag= document.createElement("p");
        dtag.innerText= i.streetAddress +" "+ i.locality +" "+ i.city +" "+ i.state +" - "+i.pinCode;
        
        addressDivBlock.append(div_but, ntag, dtag);

        addresslist_append.append(addressDivBlock) ;

    });

};



function ManageDivAddressOpen(){
    let md= document.querySelector(".manage_Address_div");
    md.style.display="none"
    
    let form= document.querySelector("#addressFrom")
    form.style.display="block";

    document.querySelector("#titalAddress").innerText= "ADD A NEW ADDRESS";


    document.querySelector("#button-maSave").onclick=(event)=>{
        let obj= {
            "aname": form.name.value,
            "aphoneNum": form.phone.value,
            "pinCode": form.pincode.value,
            "locality": form.locality.value,
            "streetAddress": form.areaAndStreet.value,
            "city": form.city.value,
            "state": form.state.value,
            "country": "India",
            "landmark": form.landmark.value,
            "aphoneNumAlternative": form.alternatePhone.value,
            "addressType": form.locationTypeTag.value, 
        }
        //event.preventDefault();
        postAddress(obj);
    }
    
}

function ManageDivAddressCancel(){
    document.querySelector(".manage_Address_div").style.display="block";
    document.querySelector("#addressFrom").style.display="none";
    let edit_div_hide=document.querySelectorAll(".addressBolckManager");
            
    edit_div_hide.forEach(i =>{
        if(i.style.display=="none"){
            i.style.display= "block";
        }
    });
    

}
Addresses(addressListData);

function removeDataToJson(index){  // this fuction help to remove old object and save it in json after done th operation
    addressListData.splice(index,1)
    localStorage.setItem("addressListData",JSON.stringify(addressListData));
}
