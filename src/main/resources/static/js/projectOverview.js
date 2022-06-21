const onDeleteProjectClicked = projectId => {
    $.ajax({
        url: '/api/project/' + projectId,
        type: 'DELETE',
        success: () => {
            location.reload();
        }
    });
}
