
async function getAllProducts(){
    try{
        let res=await fetch("http://localhost:8088/List",{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();
        //console.log(data);
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
}

async function listOfAllCategoryNameApi(gen){
    //let str=s.trim();
    let link="http://localhost:8088/Category/list/CategoryName/"+gen;
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
            listOfAllCategoryName(data);
            
        }
        
    }catch(err){
        console.log(err);
    }
    
}

async function listOfAllSubCatApi(gen, catName){
    //let str=s.trim();
    let link=`http://localhost:8088/Category/list/SubCategory/${gen}/${catName}`;
    try{
        let res=await fetch(link,{
            method:'GET',
            headers:{
                "Content-Type":"application/json",
            }
        });
        let data=await res.json();

        if(data!=undefined){
            listOfAllSubCat(data, gen, catName);
            
        }
        
    }catch(err){
        console.log(err);
    }
    
}

async function filterByCategoryApi(s, flag){

    // if(flag){
    //     document.querySelector("#resultProducts").innerHTML="";
    //     //flag= false
        
    // }
    // console.log(flag)

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
async function findProductsByCategoryApi(gender, catName, subCat){
    
    let link=`http://localhost:8088/List/Category/${gender}/${catName}/${subCat}`;
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
            //window.location.href= "Product.html"
            display(data);
            
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
//getAllProducts();
function currentSlide(n){

}

function display(arr){
    //product.innerHTML=null
    for(let i=0; i<arr.length; i++){
        let div=document.createElement("div");

        let mdiv= document.createElement("div");
        mdiv.setAttribute("class", "slideshow-container")
        //mdiv.innerHTML=`<svg id="wishList" width="28" height="28" viewBox="0 0 20 16"><path d="M8.695 16.682C4.06 12.382 1 9.536 1 6.065 1 3.219 3.178 1 5.95 1c1.566 0 3.069.746 4.05 1.915C10.981 1.745 12.484 1 14.05 1 16.822 1 19 3.22 19 6.065c0 3.471-3.06 6.316-7.695 10.617L10 17.897l-1.305-1.215z" fill="" opicity=".9"></path></svg>`;
        
        let dotDiv= document.createElement("div");
        dotDiv.setAttribute("class", "dotDiv");
        
        arr[i].images.forEach(element => {
            let image=document.createElement("img");
            image.src=element.url;
            mdiv.append(image);

            let dot= document.createElement("span");
            dot.setAttribute("class","dot");
            dotDiv.append(dot);
        });

        let brand=document.createElement("h3");
        brand.innerText=arr[i].brand;

        let name= document.createElement("p");
        name.innerText= arr[i].productName;

        let price=document.createElement("h5");
        price.innerText="₹ "+arr[i].sellPrice;

        let marketPrice= document.createElement("h5");
        marketPrice.innerText= "₹ "+arr[i].marketPrice;

        let discountPrice= document.createElement("h5");
        discountPrice.innerText=  Math.floor((arr[i].marketPrice- arr[i].sellPrice) /arr[i].marketPrice * 100)+"% off";

        let priceDiv= document.createElement("div");
        priceDiv.append(price, marketPrice, discountPrice);
        priceDiv.setAttribute("class", "priceDiv")

        let infoDiv= document.createElement("div");
        infoDiv.append(name, brand, priceDiv);
        infoDiv.setAttribute("class", "infoDiv")

        //mdiv.append(image)
        div.append(mdiv, dotDiv, infoDiv);
        document.querySelector("#resultProducts").append(div);

        let time;
        div.addEventListener("mouseenter",function(event){
            event.target.style["boxShadow"]="rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px, rgba(255, 255, 255, 0.08) 0px 1px 0px inset;";

            let slideImg= mdiv.childNodes;
            let slideDot= dotDiv.childNodes;
            let currentImg= 0; 
            let n= slideImg.length;
            //console.log(document.querySelectorAll("slideshow-containerimg"));
            
            time= setInterval(function(){
                for (let k= 0; k<n; k++){
                    slideImg[k].style.display= "none";
                    slideDot[k].className= slideDot[k].className.replace('active', '');
                }
                currentImg= (currentImg+1)% n;
                console.log(currentImg)
                slideImg[currentImg].style.display= "block";
                // slideImg[currentImg].style.position= "relative";
                slideDot[currentImg].className += ' active';

            },2000)
           

        })
        div.addEventListener("mouseleave",function(event){
            event.target.style["boxShadow"]="";
            clearInterval(time);
            console.log("not")

        })
        
    }   
}

function searchProducts(data){

    // event.preventDefault();
    // data.forEach(i=>{
    //     let div = document.createElement("div");
    //     let 
    // })

}

let data;

function callApiByCategoryName(type){
    event.preventDefault();
    // document.querySelectorAll(".cate_text2").forEach(i=> {
    //     i.addEventListener("click", function(event){
    //         let subCat= event.target.innerHTML;
    //         let catName="";
            
    //         console.log(type+"---"+subCat+"---"+catName);
    //         //filterByCategoryApi()
    //     })
    // })
    // document.querySelectorAll(".cate_text2").forEach(i=> {
    //     i.addEventListener("mouseenter", function(event){
    //         let subCat//= event.target.innerHTML;
    //         let catName="";
    //         document.querySelectorAll(".cate_drop2 p").forEach(j=>{
    //             j.onclick =(k)=>{
    //                 catName=k.target.innerHTML;
    //                 subCat= event.target.innerHTML;
    //             }
    //         });
    //         console.log(type+"---"+subCat+"---"+catName);
    //         //filterByCategoryApi()
    //     })
    // })

}

document.querySelectorAll("#category_div>div").forEach((i,iIndex)=>{

    let genderValue= "";
    let catNameValue= "_";
    let subCatValue= "_";

    i.children[0].addEventListener("click", function(event){

        document.querySelector("#resultProducts").innerHTML="";
        //console.log(i.children[1].children);
        genderValue= event.target.innerText;
        findProductsByCategoryApi(genderValue, catNameValue, subCatValue)
        listOfAllCategoryNameApi(genderValue);
        //console.log(p.length);
   
    })

    let catName= i.children[1].children;
    for(let j of catName){
        let appendDataCat= j.children[1];
        j.children[0].addEventListener("click", function(event){

            document.querySelector("#listCategory").innerHTML="";
            genderValue= i.children[0].innerText;
            catNameValue= event.target.innerText;
            subCatValue= "_";

            findProductsByCategoryApi(genderValue, catNameValue, subCatValue);
            //console.log(appendDataCat.getAttributeNames)
         
            listOfAllSubCatApi(genderValue, catNameValue);
            
        })

        let subCat= j.children[1].children[0].children
        for(let k of subCat){

            k.addEventListener("click", function(event){
                document.querySelector("#resultProducts").innerHTML="";
                genderValue= i.children[0].innerText;
                catNameValue= j.children[0].innerText;
                subCatValue= event.target.innerText;

                findProductsByCategoryApi(genderValue, catNameValue, subCatValue) ;
                //console.log(i.children[0].innerText+"-----"+j.children[0].innerText+"------"+k.innerText);

            })
            
        }
    }
})
    //let subCat= catName.children[1]
//})

function ProductFilCat(data){

   // let category= document.querySelector(".")
 

}

function ShowAllBrand(){
    
}

function searchByPrice(){
    
}

function  listOfAllCategoryName(data){
    
    let positon= document.querySelector("#listCategory");

    //console.log(data)
    positon.innerHTML= `<div id="${data[0]. categoryType}">
    <input type="checkbox" id="${data[0]. categoryType}Chk" checked>
    <p class="PCate_text"><label for="${data[0]. categoryType}Chk">${data[0]. categoryType}</label>
    <input type="checkbox" value="${data[0]. categoryType}" class="productGChk" checked></p>
    <div class="productCate_drop"></div></div>`

    //let productCate_drop= document.querySelector(".productCate_drop");


    data.forEach((i)=>{
        console.log(i.categoryName);
        listOfAllSubCatApi(i.categoryType, i.categoryName);

    })
    
}

function listOfAllSubCat(data, categoryType, categoryName){

    let mainDiv= document.createElement("div")
    mainDiv.setAttribute("class","product_Cat_Gen");

    let mInput= document.createElement("input")
    mInput.setAttribute("id",`${categoryType.charAt(0)+categoryName}`)
    mInput.type= "checkbox";

    mInput.onclick=()=>{

        findProductsByCategoryApi(categoryType, categoryName, "-");
        listOfAllSubCatApi(categoryType, categoryName);

    }
    
    let p= document.createElement("p")
    p.setAttribute("class", "pCate_text2");

    let mlable= document.createElement("lable");
    mlable.setAttribute("for", `${categoryType.charAt(0)+categoryName}`);
    mlable.innerHTML=`<i></i>${categoryName}`;

    mlable.onclick=(i)=>{
        if(mInput.checked){
            mInput.checked= false;
        }else{
            mInput.checked= true;
        }
    }

    let input= document.createElement("input")
    input.type= "checkbox";
    p.append(mlable, input);

    let div= document.createElement("div")
    div.setAttribute("class", "productCate_drop2");

    let flag= true;
    data.forEach((i)=>{

        let smallp= document.createElement("p");
        smallp.value= `${i.categoryID}`;
        smallp.onclick=()=>filterByCategoryApi(smallp.value, flag);
        flag= false;
        smallp.innerHTML=`<input type="checkbox"  id= ${i.subCategory.charAt(0)+i.categoryID}><label for=${i.subCategory.charAt(0)+i.categoryID}>${i.subCategory}</label>`;

        div.append(smallp);

    })

    mainDiv.append(mInput, p, div);

    let productCate_drop= document.querySelector(`#${categoryType}>.productCate_drop`);

    //console.log(`${data[0].categoryType}>.productCate_drop`)
    if (productCate_drop== undefined) {
        document.querySelector("#listCategory").append(mainDiv);
        mInput.checked= true;
    } 
    else {
        productCate_drop.append(mainDiv);
    }
    

}

