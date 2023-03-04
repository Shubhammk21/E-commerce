{/* <p onclick="ViewProfile()">View Profile</p>
<p onclick="ChangePass()">Change Password</p>
<p onclick="LogInHistory()">LogIn History</p>
<p onclick="UpdateProfile()">Update Profile</p>
<p onclick="LogOut()">Log Out</p> */}
//localStorage.removeItem("myProfile");
let token =JSON.parse(localStorage.getItem("token"));
//this help to run display method because i facing problem of null data pass or display not function because of   window.location.href="myProfile.html";\\
async function ViewProfile(){
    if(token==null){
        alert("LogIn frist");
    }else{
        try {
            let cusid=token.userId;
            let res= await fetch("http://localhost:8088/customer/"+cusid);
            let data= await res.json();
            if(data.message==undefined){
                //console.log(data);
                localStorage.removeItem("myProfile");
                localStorage.setItem("myProfile",JSON.stringify(data));
                window.location.href="myProfile.html";
            }else{
                alert(data.message);
            }
        } catch (error) {
            console.log(error);
        }
    }
}
async function viewProfileUpdateData(fname,lname,pass,phone,email,dob,gender){//this function help to update profile data without password 
    try {
        let uuid=token.uuId;
        let res= await fetch("http://localhost:8088/customer?key="+uuid,{
            method:'PUT',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                'customerId':token.userId,
                'dob':  dob.value,
                'firstName': fname.value,
                'gender': gender,
                'lastName' : lname.value,
                'email': email.value,
                'mobileNumber': phone.value,
                'password' : pass,
            })
        });
        let data= await res.json();
        if(data.message==undefined){
            localStorage.removeItem("myProfile");
            localStorage.setItem("myProfile",JSON.stringify(data));
        }else{
            alert(data.message);
        }
    } catch (error) {
        console.log(error);
    }
}
async function LogOut(){
    try {
        let uuid=token.uuId;
        let res= await fetch("http://localhost:8088/Customers/LogOut?key="+uuid,{
            method: 'DELETE'
            // headers:{
            //     "Content-Type":"application/json"
            // }
        });
        let data= await res;
        if(data !=null){
            localStorage.removeItem("token");
            localStorage.removeItem("myProfile");
            alert("data");
            window.location.href="index.html";  
        }
    } catch (error) {
        console.log(error)
    }
}
async function ChangePassword(newPassword){//this method used for changing password
    let myProfile= JSON.parse(localStorage.getItem("myProfile"));
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
        }

    } catch (error) {
        console.log(error);
    }
}

//******************************************************************************************************This is a Normal  JS ********************************************************* */
let logindetails= document.querySelector("#profile>div"); 

if(token==null){//this use for check if user login or not this is do its hide login user function such as view profile and logout
    logindetails.style.display="none";
}else{
    let myProfiledata=JSON.parse(localStorage.getItem("myProfile"));
    if(myProfiledata==null){
        document.querySelector("#loginnav>p").innerHTML="LogIn/SignUp";
    }else{
        document.querySelector("#loginnav>p").innerHTML=myProfiledata.firstName;
        let page= window.location.pathname;
        let pageindex= page.substring(page.lastIndexOf('/'));
        if(pageindex="myProfile.html"){//this
            display(myProfiledata);
        }
    }
}

function display(data){
    let fname=document.getElementById("fname");
    let lname=document.getElementById("lname");
    let phone=document.getElementById("username");
    let email=document.getElementById("email");
    let dob=document.getElementById("dob");
    let gender= document.getElementsByName("insex");
    for(let i=0; i<gender.length; i++){
        if(gender[i].value==data.gender){
        gender[i].checked="true";
        }
    }
    fname.value=data.firstName;
    lname.value=data.lastName;
    phone.value=data.mobileNumber;
    email.value=data.email;
    dob.value=data.dob;
    fname.style.pointerEvents="none";
    lname.style.pointerEvents="none";
    phone.style.pointerEvents="none";
    email.style.pointerEvents="none";
    dob.style.pointerEvents="none";
    document.getElementById("f2").style.pointerEvents="none";
}
function buttonMyprofile(){
    preventDefault();
    let fname=document.getElementById("fname");
    let lname=document.getElementById("lname");
    let phone=document.getElementById("username");
    let email=document.getElementById("email");
    let dob=document.getElementById("dob");
    let gender= document.getElementsByName("insex");
    fname.style.pointerEvents="fill";
    lname.style.pointerEvents="fill";
    phone.style.pointerEvents="fill";
    email.style.pointerEvents="fill";
    dob.style.pointerEvents="fill";
    document.getElementById("f2").style.pointerEvents="all";
    document.getElementById("button-myprofile").style.display="none";
    document.getElementById("button-savemyprofile").style.display="block";
};
function buttonSavemyprofile(){
    let fname=document.getElementById("fname");
    let lname=document.getElementById("lname");
    let phone=document.getElementById("username");
    let email=document.getElementById("email");
    let dob=document.getElementById("dob");
    var gender;
    let gendata= document.getElementsByName("insex");
    for(let i=0; i<gendata.length; i++){
        if(gendata[i].checked){
        gender=gendata[i].value;
        }
    }
    let pass=myProfiledata.password;
    let profiledata = viewProfileUpdateData(fname,lname,pass,phone,email,dob,gender);
    localStorage.removeItem("myProfile");
    localStorage.setItem("myProfile",JSON.stringify(profiledata));
};


//changePassValidation


