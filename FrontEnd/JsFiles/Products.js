
async function getAllProducts(){
    try{
        let res=await fetch("http://localhost:8088/List",{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        console.log(data);
        if(data!=undefined){
            show(data);
        }
    }catch(err){
        console.log(err);
    }
};

async function searchProducts(){
    let s=document.getElementById("searchInput").value;
    let link="http://localhost:8880/user/product/"+s;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        // console.log(data);
        if(data!=undefined){
            show(data);
        }
        
    }catch(err){
        console.log(err);
    }
};

async function filterByCategory(s){
    let str=s.trim();
    let link="http://localhost:8088/List/Category/"+str;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        // console.log(data);
        if(data!=undefined){
            show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    console.log(str);
}

async function filterByBrands(s){
    let str=s.trim();
    let link="http://localhost:8088/List/Brand/"+str;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        // console.log(data);
        if(data!=undefined){
            show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    console.log(str);
}

async function priceLessThan(){
    let value=document.getElementById("filterByPrice").value;
    let link="http://localhost:8088/List/UnderPrice/"+value;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        // console.log(data);
        if(data!=undefined){
            show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    console.log(str);
}