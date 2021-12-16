document.addEventListener("DOMContentLoaded", function() {
    console.log(getQueryString());
});

const search = function() {
    const searchQueryString = getSearchQueryString();
    const searchPageURL = '/gola';
    location.href = searchPageURL + searchQueryString;
};

const selectSearchOption = function(e) {
    if(e.classList.contains('selected')) {
        e.classList.remove('selected');
        return;
    }
    e.classList.add('selected');
};

const getSelectedCategoryURL = function() {
    const selectedCategoryList = document.getElementById('categoryList').getElementsByClassName('selected');
    if(selectedCategoryList.length == 0) return;
    let selectedCategoryURL = "category=";
    for(let index = 0; index < selectedCategoryList.length; index++) {
        selectedCategoryURL += selectedCategoryList[index].id + ",";
    }
    return selectedCategoryURL.slice(0, -1);
};

const getOrderOptionURL = function() {
    const isOrderByTime = document.getElementById('orderByTime').checked;
    const isOrderByCount = document.getElementById('orderByCount').checked;
    if(!isOrderByTime && !isOrderByCount) return;
    let checkedOrderURL = "order=";
    if(isOrderByTime) checkedOrderURL += "time,";
    if(isOrderByCount) checkedOrderURL += "count,";
    return checkedOrderURL.slice(0, -1);
};

const getSearchValueURL = function() {
    const searchValue = document.getElementById('searchBar').value;
    if(!searchValue) return;
    return "search=" + searchValue;
};

const clickRandomSearch = function() {
    location.href= '/gola?' + 'random=true';
};

const getSearchQueryString = function() {
    const categoryURL = getSelectedCategoryURL();
    const orderURL = getOrderOptionURL();
    const searchURL = getSearchValueURL();
    if(!categoryURL && !orderURL && !searchURL) return "";
    let queryString = "?";
    if(categoryURL) queryString += categoryURL + "&";
    if(orderURL) queryString += orderURL + "&";
    if(searchURL) queryString += searchURL + "&";
    return queryString.slice(0, -1);
};