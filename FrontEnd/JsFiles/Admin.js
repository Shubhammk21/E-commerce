async function AddProductApi(data,image,catId){
    try{
        let res=await fetch("http://localhost:8088/Add/Product"+catId,{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                "brand": "string",
                "dimension": "string",
                "images": image,
                "marketPrice": 0,
                "productId": "string",
                "productName": "string",
                "quantity": 0,
                "sellPrice": 0,
                "specification": "string"
            })
        });
        let data= await  res.json();
            //console.log(data);
        if(data.message!=undefined){
            alert(data.message);
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
    let catType= document.querySelector("#subCategory").value;
    //console.log(catType)
    try {
        let res= await fetch ('http://localhost:8088/Category/list/CategoryName/'+catType);// this api call the all the Category names by Category type;
        let data= await res.json();
            //console.log(data);
        if(data!=null){
            AddCatName(data)
            //console.log(data)
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
        console.log(i);
        let op1= document.createElement("option");
        op1.innerText=i;

        apendOp.append(op1);
    });
}