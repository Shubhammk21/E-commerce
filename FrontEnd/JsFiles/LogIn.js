//console.log("working")
async function doLogin(pass,phone){
    try{
        let res=await fetch("http://localhost:8088/Customers/Login",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'password':pass.value,
                'username':phone.value
            })
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
               let token=checkLogin(data.customerId);
                localStorage.setItem("token",JSON.stringify(token));
                alert("Login Successfull!!!");
            }
    }catch(err){
        console.log(err);
    }
    async function checkLogin(id){
        let ss=id;
        //console.log(id);
       try{
            let res=await fetch("http://localhost:8088/LogIn/ActiveStatus/%7Bid%7D?cusID="+ss,{ //this api store key for example ramdom generator string value 
                method:'GET',
                headers:{
                    "Content-Type":"application/json"
                }
            });
                let data=await res.json();
                //console.log(data)
                if(data.message==undefined){
                    //key=data;
                    localStorage.setItem("user",JSON.stringify(data));
                }else{
                    alert(data.message);
                }
        }catch(err){
            console.log(err);
        }
    }
}
//console.log("working");
document.querySelector("#button-86").addEventListener("click",function(event){
    //console.log("working");
    event.preventDefault();
     let phone=document.getElementById("username");
    let pass=document.getElementById("password");
    if(phone.value=="" || pass.value=="" ){
        alert("Fill all the Inputs!!");
    }
    console.log(phone.value,pass.value);
    let uuid=doLogin(pass,phone);
});
document.getElementById("loginfun").addEventListener("click",function(event){
    console.log("working");
    let log= document.getElementById("login");
    let nav=document.querySelector("#navbar");
    nav.style.filter="blur(8px)";
    let main=document.querySelector("#main");
    main.style.filter="blur(8px)";
    log.style.display="block";
    nav.style.userSelect="none";
    main.style.userSelect="none";
    nav.addEventListener("dblclick",function(e){
        console.log("working")
        nav.style.filter="none"
        main.style.filter="none";
        log.style.display="none";
        nav.style.userSelect="auto";
        main.style.userSelect="auto";
    })
    main.addEventListener("dblclick",function(e){
        console.log("working")
        nav.style.filter="none"
        main.style.filter="none";
        log.style.display="none";
    })

});
document.getElementById("logback").addEventListener("click",function(event){
    let log= document.getElementById("login");
    document.querySelector("#navbar").style.filter="none"
    document.querySelector("#main").style.filter="none";
    log.style.display="none";
    document.querySelector("#navbar").nav.style.userSelect="auto";
    document.querySelector("#main").main.style.userSelect="auto";
});