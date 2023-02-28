//console.log("working")
async function signUp(fname,lname,user,pass,dob,gender){
    try{
        let res=await fetch("http://localhost:8088/SignUp",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'dob':  dob.value,
                'firstName': fname.value,
                'gender': gender,
                'lastName' : lname.value,
                'password' : pass.value,
                'username': user.value
            })
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Sign Up Successfull!!!");
                return data;
            }
    }catch(err){
        console.log(err);
    }
}
//console.log("working");
document.querySelector("#button-86").addEventListener("click",function(event){
    //console.log("working");
    //event.preventDefault();
    let fname=document.getElementById("fname");
    let lname=document.getElementById("lname");
    let user=document.getElementById("username");
    let pass=document.getElementById("password");
    let dob=document.getElementById("dob");
    var gender;
    let gendata= document.getElementsByName("insex");
    for(let i=0; i<gendata.length; i++){
        if(gendata[i].checked){
           gender=gendata[i].value;
        }
    }
    let profiledat = signUp(fname,lname,user,pass,dob,gender);
    localStorage.setItem(profiledat.fname,JSON.stringify(profiledat))
    // if(phone.value=="" || pass.value=="" ){
    //     alert("Fill all the Inputs!!");
    // }
    //console.log(fname.value+"\n"+lname.value+"\n"+user.value+"\n"+pass.value+"\n"+dob.value+"\n"+gender);
   // let uuid=doLogin(pass,phone);
    });