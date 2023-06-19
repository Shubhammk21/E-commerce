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
                //localStorage.removeItem("addressListData");
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
            let res= await fetch('http://localhost:8080/Add/Address/'+customer.customerId,{// this api post the address
                method:'POST',
                headers:{
                    "Content-Type":"application/json"
                },
                body:JSON.stringify({
                   
                    "addressType": data.addressType,
                    "aname": data.name,
                    "aphoneNum": data.mobileNumber,
                    "aphoneNumAlternative": data.AlternativeNumber,
                    "city": data.city,
                    "country": data.country,
                    "landmark": data.landmark,
                    "locality": data.locality,
                    "pinCode": data.pinCode,
                    "state": data.state,
                    "streetAddress": data.streetAddress

                })
            });

            let apiData= await res.json();
            if(apiData!=null){
                localStorage.removeItem("addressListData");
                localStorage.setItem("addressListData",JSON.stringify(data));
            }

        } catch (error) {
            console.log(error);
        }
    }
}


/****************************************************************************************************** This is a Normal  JS  *********************************************************************/

function Addresses(addresses){ // this method create addreses list tables 
    //console.log(addresses);
    addresses.forEach(i=>{

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
        let deletePop= document.createElement("p");
        deletePop.innerText= "Delete";

        popDeleteEdit.append(editPop,deletePop);



    //888888888888888888888888888888888888888888888888    END THis Section    888888888888888888888888888888888888888888888888888888888888888888\\
        div_but.append(button, popDots);
        div_but.setAttribute("class", "diff_but_dot");

        let ntag= document.createElement("p");
        ntag.innerText= i.aname +"        "+ i.aphoneNum;
        ntag.style.fontWeight="600";

        let dtag= document.createElement("p");
        dtag.innerText= i.streetAddress +" "+ i.locality +" "+ i.city +" "+ i.state +" - "+i.pinCode;
        
        //let appendDiv= document.getElementById("manage_Addresses");
        addresslist_append.append(div_but, ntag, dtag) ;

    });

};
function ManageDivAddressOpen(){
    let md= document.querySelectorAll(".manage_Address_div");
    for(let i=0; i<md.length; i++){
        md[i].style.display="none"
    }
   
    let fd= document.querySelector("#addressFrom")
    fd.style.display="block";
    
}
function ManageDivAddressCancel(){
    document.querySelector(".manage_Address_div").style.display="block";
    document.querySelector("#addressFrom").style.display="none";

}
Addresses(addressListData);

