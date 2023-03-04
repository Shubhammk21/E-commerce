let myProfile= JSON.parse(localStorage.getItem("myProfile"));
let token =JSON.parse(localStorage.getItem("token"));
//console.log("jhghjg")
async function ChangePassword(newPassword){//this method used for changing password
   //preventDefault()
    try {
        let res= await fetch("http://localhost:8088/customer?key="+token.uuId,{
            method:'PUT',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                'customerId':myProfile.userId,
                'dob':  myProfile.dob,
                'firstName': myProfile.firstName,
                'gender': myProfile.gender,
                'lastName' : myProfile.lastName,
                'email': myProfile.email,
                'mobileNumber': myProfile.mobileNumber,
                'password' : newPassword
            })
        });
        let data= await res.json();
        if(data.message==undefined){
            localStorage.removeItem("myProfile");
            localStorage.setItem("myProfile",JSON.stringify(data));
            alert("Password changed ");
        }

    } catch (error) {
        console.log(error);
    }
}
let check =document.querySelectorAll("form>i");

function checkPassValidation(){
    let checkValidation= true;
    let newPass= document.getElementById("new_pass").value;
    
    newPass= newPass.trim();
    

    //this check length and mark 
    if(newPass.length>=8 && newPass.length<=15){
        document.getElementById("changePass_div_p1").style.textDecoration="line-through";
    }else if(newPass !=null){
        checkValidation= false;
        document.getElementById("changePass_div_p1").style.textDecoration="none";
    }

    //this check  number in password and mark 
    if(newPass.match(/[0-9]/i)){
        document.getElementById("changePass_div_p2").style.textDecoration="line-through";
    }else if(newPass !=null){
        checkValidation= false;
        document.getElementById("changePass_div_p2").style.textDecoration="none";
    }

     //this check  alphabate letter and mark 
    if(newPass.match((/[A-Za-z]/i))){
        document.getElementById("changePass_div_p3").style.textDecoration="line-through";
    }else if(newPass !=null){
        checkValidation= false;
        document.getElementById("changePass_div_p3").style.textDecoration="none";
    }
    //this check spacial charecter and mark 
    if(newPass.match(/[^A-Za-z0-9-' ']/i)){
        document.getElementById("changePass_div_p4").style.textDecoration="line-through";
    }else if(newPass !=null){
        checkValidation= false;
        document.getElementById("changePass_div_p4").style.textDecoration="none";
    }
    if(checkValidation){
        check[1].style.display="block";
    }else{
        check[1].style.display="none";
    }
    return checkValidation;
    
}

document.getElementById("button-ChangePass").addEventListener("click",function (event){
    event.preventDefault();
    let newPass= document.getElementById("new_pass").value;
    let oldPass= document.getElementById("old_pass").value;
    let renew= document.getElementById("renew_pass").value;
    let output= document.getElementById("output");
    if(oldPass==myProfile.password && newPass !=null){
        if(checkPassValidation()){
            if(newPass==renew && newPass !=null){
                ChangePassword(newPass);
                console.log(newPass.length);
                output.innerHTML="";
            }
            else{
                output.innerHTML='<i class="fa-solid fa-triangle-exclamation"></i>'+"   Re-enter password not match";
            }
        }else{
            output.innerHTML='<i class="fa-solid fa-triangle-exclamation"></i>'+"   Password not matching criteria";
        }
    }else{
        output.innerHTML= '<i class="fa-solid fa-triangle-exclamation"></i>'+"   Incorrect password";
    }
});

function checkOldPass(){
    let oldPass= document.getElementById("old_pass").value;
    if(oldPass==myProfile.password){
        check[0].style.display="block";
    }else{
        check[0].style.display="none";
    }
}

function checkReEnterPass(){
    let oldPass= document.getElementById("old_pass").value;
    if(oldPass==myProfile.password){
        check[2].style.display="block";
    }else{
        check[2].style.display="none";
    }
}
