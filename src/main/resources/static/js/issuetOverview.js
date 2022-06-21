const  onDeleteIssueClicked = issueId =>{
    $.ajax({
        url:'/api/issue/'+ issueId,
        type:'DELETE',
        success:()=>{
            //response for error code 200
            location.reload();
        }
    });
}
