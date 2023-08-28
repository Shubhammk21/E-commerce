
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
            //show(data);
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
            //show(data);
        }
        
    }catch(err){
        console.log(err);
    }
};

async function filterByCategory(s){
    //let str=s.trim();
    let link="http://localhost:8088/List/Category/"+s;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        console.log(data);
        if(data!=undefined){
            //show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    
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
            //show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
   
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
            //show(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    
}

//####################################################################### Normal JS Code #######################################\\

function callApiByCategoryName(){
    filterByCategory(1);
}

function display(arr){
    product.innerHTML=null
    for(let i=0;i<arr.length;i++){
        let div=document.createElement("div");
        div.addEventListener("mouseenter",function(event){
            event.target.style["boxShadow"]="rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset";
        })
        div.addEventListener("mouseleave",function(event){
            event.target.style["boxShadow"]="";
        })
        let mdiv=document.createElement("div");
        let image=document.createElement("img");
        image.src=arr[i].image_url;
        let name=document.createElement("h3");
        name.innerText=arr[i].name;
        let add=document.createElement("button");
        add.innerText="Add To Cart";
        add.addEventListener("click",function(event){
            event.target.style.backgroundColor="green"
            event.target.innerText="🗸 "+"Added";
            adddata(arr[i]);
        })

        let price=document.createElement("h4");
        price.innerText="Price: "+arr[i].price;

        let stoff=document.createElement("h4");
        stoff.innerText="strikedoffprice: "+arr[i].strikedoffprice;

        mdiv.append(image)
        document.querySelector("#product").append(div);
        div.append(mdiv,name,price,stoff,add);
        
    }   
}