async function AddProductApi(data,catId){
    try{
        let res=await fetch("http://localhost:8088/Add/Product/"+catId,{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify(data)
            // body: JSON.stringify({
            //     "brand": data.brand,
            //     "dimension": data.dimension,
            //     "images": data.images,
            //     "marketPrice": data.marketPrice,
            //     "productName": data.productName,
            //     "quantity": data.quantity,
            //     "sellPrice": data.sellPrice,
            //     "specification": data.specification
            // })
        
        });
        let addeddata= await res.json();
            //console.log(data);
        if(addeddata.message!=undefined){
            alert(addeddata.message);
        }else{
            alert("Added Successfull!!!");
                window.location.reload();
            }
    }catch(err){
        console.log(err);
    }
}

async function CategoryOption(){
    let catType= document.querySelector("#catTypeSelecter").value;
    //console.log(catType)
    try {
        let res= await fetch ('http://localhost:8088/Category/list/CategoryName/'+catType);// this api call the all the Category names by Category type;
        let data= await res.json();
            //console.log(data);
        if(data!=null){
            AddSubCat(data)
            //console.log(data)
        }
            
    } catch (error) {
        console.log(error)
    }

}

async function CategoryNameOption(){
    let subCat= document.querySelector("#subCategory").value;
    let catType= document.querySelector("#catTypeSelecter").value;
    //console.log(subCat)
    try {
        let res= await fetch (`http://localhost:8088/Category/list/SubCategory/${catType}/${subCat}`);// this api call the all the Category names by Category type;
        let data= await res.json();
           
        if(data!=null){
            AddCatName(data)
            console.log(data)
        }
            
    } catch (error) {
        console.log(error)
    }
}

function AddSubCat(optionData){
    
    let apendOp= document.querySelector("#subCategory");
    optionData.forEach(i => {
        //console.log(i);
        let op1= document.createElement("option");
        op1.innerText=i;

        apendOp.append(op1);
    });
}

function AddCatName(optionData){
    
    let apendOp= document.querySelector("#categoryName");
    optionData.forEach(i => {
        //console.log(i);
        let op1= document.createElement("option");
        op1.innerText=i.subCategory;
        op1.value= i.categoryID;

        apendOp.append(op1);
    });
}

let form= document.querySelector("#addProductFrom");
form.addEventListener("submit", function(event) {
    event.preventDefault();
    let image= form.images.value.split(" ");
    console.log(image)
    let finalImage=[];
    image.forEach(i=>{
        let obj={"url":i};
        finalImage.push(obj);
    });
    let obj= {
        "brand": form.brand.value,
        "dimension": form.dimension.value,
        "images": finalImage,
        "marketPrice": +form.marketPrice.value,
        "productName": form.productName.value,
        "quantity": +form.quantity.value,
        "sellPrice": +form.sellPrice.value,
        "specification": form.specification.value
    }
       
    console.log(obj);
    let categoryId= form.categoryName.value;
    console.log(categoryId)
    AddProductApi(obj,categoryId)

})
