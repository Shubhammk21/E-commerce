let SearchData =async ()=>{

    let input=document.getElementById("input");
    input.style.transition="500ms"
    input.style.width="100%"

    let top1=document.getElementById("topbar");
    top1.style.padding="0px"
    top1.style.width="30%"
    
    top1.addEventListener("mouseenter",()=>{
        search.style.display="block"
        // search.style.width="80%"
        input.style.width="30%"
        // top1.style.justifyContent="space-between"    
    })
    top1.addEventListener("mouseleave",()=>{
        search.style.display="none"
        // search.style.width="80%"
        input.style.width="100%"
        // top1.style.justifyContent="space-between"    
    })
}
let products= document.getElementById("product");

