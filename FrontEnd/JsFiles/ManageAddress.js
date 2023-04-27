document.querySelector("#AddressList");

let customerId= JSON.parse(localStorage.getItem("toten"))|| [];

async function addressList(){
    if(token==null){
        window.location.href="/FrontEnd\signUp.html";
    }else{
        try {
            let res= await fetch (`http://localhost:8088/GetAddressList/${customerId.customerId}`);
            let data= await res.json();
            Addresses(data);
            
        } catch (error) {
            console.log(error)
        }
    }

}
function postAddress(){
    
}

function Addresses(addresses){// this method create addreses list tables 
    addresses.foreach(i =>{

        let div_but= document.createElement("div");

        let button= document.createElement("button");
        button.innerText= addresses.addressType;

        let dots= document.createElement("button");
        dots.innerText= `<i class="fa-solid fa-ellipsis-vertical"></i>`;

        div_but.append(button, dots);
        div_but.setAttribute("class", "diff_but_dot");

        let ntag= document.createElement("p");
        ntag.innerText= addresses.name +"           "+ addresses.mobileNumber;

        let dtag= document.createElement("p");
        dtag.innerText= addresses.streetAddress +" "+ addresses.locality +" "+ addresses.city +" "+ addresses.state +" - "+addresses.pinCode;
        
        let appendDiv= document.getElementById("manage_Addresses");
        appendDiv.append(div_but, ntag, dtag);

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
