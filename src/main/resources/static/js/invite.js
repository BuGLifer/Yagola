document.addEventListener("DOMContentLoaded", function() {
    setMilestonePath(' > 모집');
    setOrderCard();
    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});

const createOrderCard = function(order) {
    console.log(order);
    let orderCardDiv = document.createElement('div');
    orderCardDiv.classList.add('orderCard');
    let restaurantInfoDiv = document.createElement('div');
    restaurantInfoDiv.classList.add('restaurantInfo');
    let guestInfoDiv = document.createElement('div');
    guestInfoDiv.classList.add('guestInfo');
    let orderInfoDiv = document.createElement('div');
    orderInfoDiv.classList.add('orderInfo');
    orderInfoDiv.append(restaurantInfoDiv);
    orderCardDiv.append(orderInfoDiv);
    orderCardDiv.append(guestInfoDiv);
    return orderCardDiv;
};

const setOrderCard = function() {
    let orderCardArea = document.getElementById('orderCardArea');
    orderCardArea.classList.add('orderCardArea');
    const testOrder = {
        "seq" : 1
        , "status" : "ONLINE"
        , "createdTime" : "2021-12-18 13:00:00"
        , "host" : {
            "seq" : 1
            , "nickName" : "길로그"
        }
        , "guest" : [
            {
                "seq" : 2
                , "nickName" : "참가자1"
                , "menu" : {
                    "seq" : 1
                    , "name" : "닭가슴살"
                }
            }
            , {
                "seq" : 3
                , "nickName" : "참가자3"
                , "menu" : {
                    "seq" : 2
                    , "name" : "고구마"
                }
            }
            , {
                "seq" : 4
                , "nickName" : "참가자4"
                , "menu" : {
                    "seq" : 3
                    , "name" : "야채믹스"
                }
            }
        ]
        , "restaurant" : {
            "seq" : 1
            , "name" : "골목식당"
        }
    };
    orderCardArea.append(createOrderCard(testOrder));
};