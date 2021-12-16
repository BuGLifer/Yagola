const search = function() {
    console.log(getSelectedCategory());
    const searchValue = document.getElementById('searchBar').value;
    const orderByTime = document.getElementById('orderByTime').checked;
    const orderByCount = document.getElementById('orderByCount').checked;
    const url = '/gola';

    let queryString = '?orderByTime=' + orderByTime;
    queryString += '&orderByCount=' + orderByCount;
    queryString += '&search=' + searchValue;
    //location.href = url + queryString;
};

const selectSearchOption = function(e) {
    if(e.classList.contains('selected')) {
        e.classList.remove('selected');
        return;
    }
    e.classList.add('selected');
};

const getSelectedCategory = function() {
    const selectedCategoryList = document.getElementById('categoryList').getElementsByClassName('selected');
    let selectedCategoryURL = "";
    if(selectedCategoryList.length == 0) return selectedCategoryURL;
    selectedCategoryURL = "category=";
    for(let index = 0; index < selectedCategoryList.length; index++) {
        selectedCategoryURL += selectedCategoryList[index].id + "&";
    }
    selectedCategoryURL = selectedCategoryURL.slice(0, -1);
    return selectedCategoryURL;
};