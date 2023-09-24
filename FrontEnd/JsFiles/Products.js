
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
            display(data);
        }
    }catch(err){
        console.log(err);
    }
};

async function searchProductsApi(){
    let s= document.getElementById("ivalue").value;

    document.querySelector("#resultProducts").innerHTML="";

    let link="http://localhost:8088/List/Search/"+s;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        if(data!=undefined){
            display(data);
            console.log(data)
        }
        
    }catch(err){
        console.log(err);
    }
};

async function filterByCategoryApi(s){
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

// function callApiByCategoryName(){
//     filterByCategory(1);
// }
getAllProducts();

function display(arr){
    //product.innerHTML=null
    for(let i=0; i<arr.length; i++){
        let div=document.createElement("div");

        div.addEventListener("mouseenter",function(event){
            event.target.style["boxShadow"]="rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset";
        })
        div.addEventListener("mouseleave",function(event){
            event.target.style["boxShadow"]="";
        })

        let mdiv= document.createElement("div");

        arr[i].images.forEach(element => {
            let image=document.createElement("img");
            image.src=element.url;
            mdiv.append(image);
        });

        let name=document.createElement("h3");
        name.innerText=arr[i].brand;

        let add=document.createElement("p");
        add.innerText= arr[i].productName

        // add.addEventListener("click",function(event){
        //     event.target.style.backgroundColor="green"
        //     event.target.innerText="🗸 "+"Added";
        //     adddata(arr[i]);
        // })

        let price=document.createElement("h5");
        price.innerText="Price: "+arr[i].sellPrice;

        let marketPrice= document.createElement("h5");
        marketPrice.innerText= arr[i].marketPrice;

        //mdiv.append(image)
        div.append(mdiv,name,price,marketPrice,add);
        document.querySelector("#resultProducts").append(div);
        
    }   
}

function searchProducts(data){

    // event.preventDefault();
    // data.forEach(i=>{
    //     let div = document.createElement("div");
    //     let 
    // })

}


function callApiByCategoryName(type){
    event.preventDefault();
    document.querySelectorAll(".cate_text2").forEach(i=> {
        i.addEventListener("click", function(event){
            let subCat= event.target.innerHTML;
            let catName="";
            
            console.log(type+"---"+subCat+"---"+catName);
            //filterByCategoryApi()
        })
    })
    document.querySelectorAll(".cate_text2").forEach(i=> {
        i.addEventListener("mouseenter", function(event){
            let subCat//= event.target.innerHTML;
            let catName="";
            document.querySelectorAll(".cate_drop2 p").forEach(j=>{
                j.onclick =(k)=>{
                    catName=k.target.innerHTML;
                    subCat= event.target.innerHTML;
                }
            });
            console.log(type+"---"+subCat+"---"+catName);
            //filterByCategoryApi()
        })
    })

}

function ProductFilCat(data){

    let passSel= "#"+data+">.productCate_drop";
    
    let pCatDrop= document.querySelector(passSel);
    pCatDrop.style.opacity="1";
    pCatDrop.style.transform= "translateY(0)";
    pCatDrop.style.pointerEvents= "auto";
    pCatDrop.style.backgroundColor= "none"
    pCatDrop.style.position= "static";

    let passSel2= passSel+">.cate_mens";
    document.querySelectorAll(passSel2).forEach((i)=>{
        //console.log(i);
        i.addEventListener("click", function(event){
            
            let pCatDrop2= i.children[1];
            pCatDrop2.style.opacity="1";
            pCatDrop2.style.transform= "translateY(0)";
            pCatDrop2.style.pointerEvents= "auto";
            pCatDrop2.style.backgroundColor= "none"
            pCatDrop2.style.position= "static";
            
        })
        
    })


}

function ShowAllBrand(){
    
}

