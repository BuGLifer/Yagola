document.addEventListener("DOMContentLoaded", function() {
    keepSelectedOptions();
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
    const isOrderByTime = document.getElementById('time').checked;
    const isOrderByCount = document.getElementById('count').checked;
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

const keepSelectedOptions = function() {
    const queryString = getQueryString();
    console.log(queryString);
    if(queryString.random) {
        document.getElementById('randomSearch').classList.add('selected');
        return;
    }
    if(queryString.category) {
        const categories = queryString.category.split(',');
        for(let index = 0; index < categories.length; index ++) {
            document.getElementById(categories[index]).classList.add('selected');
        }
    }
    if(queryString.order) {
        const orderOptions = queryString.order.split(',');
        const isOrderByTime = document.getElementById('time').checked = false;
        const isOrderByCount = document.getElementById('count').checked = false;
        for(let index = 0; index < orderOptions.length; index ++) {
            document.getElementById(orderOptions[index]).checked = true;
        }
    }
    if(queryString.search) {
        document.getElementById('searchBar').value = queryString.search;
    }
};