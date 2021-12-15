const search = function() {
    const searchValue = document.getElementById('searchBar').value;
    const orderByTime = document.getElementById('orderByTime').checked;
    const orderByCount = document.getElementById('orderByCount').checked;
    const url = '/gola';

    let queryString = '?orderByTime=' + orderByTime;
    queryString += '&orderByCount=' + orderByCount;
    queryString += '&search=' + searchValue;
    location.href = url + queryString;
};