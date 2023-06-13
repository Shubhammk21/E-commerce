let addresslist_append=document.querySelector("#AddressList");

let customer= JSON.parse(localStorage.getItem("token"))|| [];

//let addressListData= JSON.parse(localStorage.getItem("addressListData")) || [];

async function addressList(){
    if(token==null){
        window.location.href="/FrontEnd\signUp.html";
    }else{
        try {
            let res= await fetch ("http://localhost:8088/GetAddressList/"+customer.userId);// this api call the all the address by customer id;
            let data= await res.json();
            if(data!=null){
                //localStorage.removeItem("addressListData");
                localStorage.setItem("addressListData",JSON.stringify(data));
            }
            
        } catch (error) {
            console.log(error)
        }
    }

}
console.log(customer.userId);
async function postAddress(data){
    if(token==null){
        window.location.href="/FrontEnd\signUp.html";
    }
    else{
        try {
            
            let res= await fetch(` http://localhost:8080/Add/Address/${customer.customerId}`,{// this api post the address
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

function Addresses(addresses){// this method create addreses list tables 
    addresses.foreach(i =>{

        let div_but= document.createElement("div");

        let button= document.createElement("button");
        button.innerText= i.addressType;

        let dots= document.createElement("button");
        dots.innerText= `<i class="fa-solid fa-ellipsis-vertical"></i>`;

        div_but.append(button, dots);
        div_but.setAttribute("class", "diff_but_dot");

        let ntag= document.createElement("p");
        ntag.innerText= addresses.name +"           "+ addresses.mobileNumber;

        let dtag= document.createElement("p");
        dtag.innerText= addresses.streetAddress +" "+ addresses.locality +" "+ addresses.city +" "+ addresses.state +" - "+addresses.pinCode;
        
        //let appendDiv= document.getElementById("manage_Addresses");
        addresslist_append.append(div_but, ntag, dtag) ;

    });

};
function ManageDivAddressOpen(){
    let md= document.querySelector(".manage_Address_div");
    md.style.display="none"
    let fd= document.querySelector("#addressFrom")
    fd.style.display="block";
    
}
function ManageDivAddressCancel(){
    document.querySelector(".manage_Address_div").style.display="block";
    document.querySelector("#addressFrom").style.display="none";

}

