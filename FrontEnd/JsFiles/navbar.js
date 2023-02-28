{/* <p onclick="ViewProfile()">View Profile</p>
<p onclick="ChangePass()">Change Password</p>
<p onclick="LogInHistory()">LogIn History</p>
<p onclick="UpdateProfile()">Update Profile</p>
<p onclick="LogOut()">Log Out</p> */}
//localStorage.removeItem("myProfile");
let token =JSON.parse(localStorage.getItem("user"))||[];
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
async function viewProfileUpdateData(fname,lname,pass,phone,email,dob,gender){
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
                'password' : 'Mp21mk6293@',
            })
        });
        let data= await res.json();
        if(data.message==undefined){
            console.log(data);
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

let data=JSON.parse(localStorage.getItem("myProfile"));
display(data);
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
document.getElementById("button-myprofile").addEventListener("click",function(event){
    event.preventDefault();
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
});
document.getElementById("button-savemyprofile").addEventListener("click",function(event){
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
    let pass=data.password;
    let profiledata = viewProfileUpdateData(fname,lname,pass,phone,email,dob,gender);
    localStorage.removeItem("myProfile");
    localStorage.setItem("myProfile",JSON.stringify(profiledata));
});

