const DeleteIssueWhenClicked = issueId => {
    console.log("Delete button");
    console.log(issueId);
    $.ajax({
        url: '/api/issue/delete/' + issueId,
        type: 'DELETE',
        success: () => {
            //response for error code 200
            location.reload();
        }
    });
};
