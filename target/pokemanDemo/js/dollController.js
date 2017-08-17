/**
 * Created by zhm on 2017/7/20.
 */
/**
 *  这里要说明下 data是一个DTO对象
 * @param name
 * @param sno
 */
var app = angular.module('myApp', []);
app.controller('myController', ['$scope', '$http', function ($scope, $http) {
    $scope.num = '';
    $scope.model = {
        showContentTab_one: true,
        showContentTab_two: true,
        clickTab: 1,
        dollGroupDto: {},
        selectIdList: [],
        selectDollList: []
    };
    $scope.dollModel = {
        dollQuery: {//玩偶查询条件
            dollName: '',
            dollLevel: 0,
            dollType: 0
        },
        dollInfoList: {},//玩偶列表
        dollAddDto: {//创建玩偶
            dollId: '',
            dollName: '',
            dollLevel: 1,
            dollType: 1,
            dollChildList: []
        }
    };
    $scope.dollLevels = [
        {id: 1, showName: '橙色'},
        {id: 2, showName: '紫色'},
        {id: 3, showName: '蓝色'},
    ];
    $scope.dollTypes = [
        {id: 1, showName: '单玩偶'},
        {id: 2, showName: '机器人'},
    ];

    $scope.events = {
        changeShowTab_one: function () {
            $scope.model.showContentTab_one = !$scope.model.showContentTab_one;
        },
        changeShowTab_two: function () {
            $scope.model.showContentTab_two = !$scope.model.showContentTab_two;
        },
        /**
         * 切换菜单
         * @param type
         */
        clickTab: function (type) {
            $scope.model.clickTab = type;
            $scope.model.selectIdList = [];
            $scope.model.selectDollList = [];
        },
        /**
         * 创建玩偶
         */
        createDollInfo: function () {
            /**
             * 写法1：  存在对象传递问题
             */
            $http.post('http://localhost:8090/controller/testAction/saveDollInfo',
                {
                    dollName: $scope.dollModel.dollAddDto.dollName,
                    dollLevel: $scope.dollModel.dollAddDto.dollLevel,
                    dollType: $scope.dollModel.dollAddDto.dollType,
                    dollChildList: $scope.model.selectDollList
                }
            ).success(function (response) {
                if(response.state=='success'){
                    alert('----------------插入数据库成功------------------');
                }else {
                    alert('----- ----------插入数据库失败------------------');
                }
            }).error(function (response) {
                alert('----- ----------插入数据库失败------------------');
            });
            /**
             * 写法2：
             */
            //$http({
            //    method: 'POST',
            //    url: 'http://localhost:8090/controller/testAction/saveDollInfo',
            //    data: JSON.stringify($scope.dollModel.dollAddDto)
            //}).then(function successCallback(response) {
            //    alert('----------------插入数据库成功------------------');
            //
            //}, function errorCallback(response) {
            //    alert('----- ----------插入数据库失败------------------');
            //});
            /**
             * 写法3：
             */
            //$.ajax({
            //    type:"POST",
            //    url:"http://localhost:8090/controller/testAction/saveDollInfo",
            //    data: JSON.stringify($scope.dollModel.dollAddDto),//必要
            //    dataType:"json",
            //    contentType:"application/json",
            //    async: false,
            //    cache:false,
            //    success:function(data){
            //        if(data.status=="success"){
            //            alert('----------------插入数据库成功------------------');
            //        }else{
            //            alert('----- ----------插入数据库失败------------------');
            //        }
            //    }
            //});
        },
        /**
         * 根据条件查询玩偶
         */
        findDollInfoList: function () {
            /**
             * 写法1：
             */
            $http.get('http://localhost:8090/controller/testAction/findDollInfoList', {
                    params: {
                        dollName: $scope.dollModel.dollQuery.dollName,
                        dollLevel: $scope.dollModel.dollQuery.dollLevel,
                        dollType: $scope.dollModel.dollQuery.dollType
                    }
                })
                .then(function (response) {
                    $scope.dollModel.dollInfoList = response.data;
                });
            /**
             * 写法2：
             */
            //$http({
            //    method: 'GET',
            //    url: 'http://localhost:8090/controller/testAction/findDollInfoList'
            //}).then(function successCallback(response) {
            //    $scope.dollModel.dollInfoList=response.data;
            //}, function errorCallback(response) {
            //
            //});
            /**
             * 写法3：
             */
            //$.ajax({
            //    type:"GET",
            //    url:"http://localhost:8090/controller/testAction/findDollInfoList",
            //    success:function(result){
            //        $scope.dollModel.dollInfoList=result;
            //        console.log($scope.dollModel.dollInfoList);
            //    }
            //});
        },

        /**
         * 根据条件查询玩偶
         */
        findDollOnClick: function (dateItem) {
            $http.get('http://localhost:8090/controller/testAction/findDollInfoList', {
                    params: {
                        dollName: dateItem.dollName,
                        dollLevel: dateItem.dollLevel,
                        dollType: dateItem.dollType
                    }
                })
                .then(function (response) {
                    $scope.dollModel.dollInfoList = response.data;
                });
        },
        /**
         * 删除玩偶
         * @param dateItem
         */
        deleteDollInfoById: function (dateItem) {
            /**
             * 写法2：
             */
            $http({
                method: 'POST',
                url: 'http://localhost:8090/controller/testAction/deleteDollInfoById/' + dateItem.dollId,
            }).then(function successCallback(response) {
                alert('----------------删除成功------------------');
                //setTimeout(this.findDollInfoList,1000);
            }, function errorCallback(response) {
                alert('----- ----------删除失败------------------');
            });
        },

        selectDoll: function (dateItem) {
            $scope.model.selectIdList.push(dateItem.dollId);
            $scope.model.selectDollList.push(dateItem);

        },

        cancelSelect: function (dateItem) {
            angular.forEach($scope.model.selectIdList, function (data, index) {
                if (data == dateItem.dollId) {
                    $scope.model.selectIdList.splice(index, 1);
                    return;
                }
            });
            angular.forEach($scope.model.selectDollList, function (data, index) {
                if (data.dollId == dateItem.dollId) {
                    $scope.model.selectDollList.splice(index, 1);
                    return;
                }
            });
        },

        isSelected: function (id) {
            var isSelected = false;
            angular.forEach($scope.model.selectIdList, function (data, index) {
                if (data == id) {
                    isSelected = true;
                }
            });
            return isSelected;
        },

        saveDollGroup: function () {
            $http.post('http://localhost:8090/controller/testAction/saveDollGroup',
                {
                    dollGroupName: $scope.model.dollGroupDto.dollGroupName,
                    dollIdList: $scope.model.selectIdList
                }
            ).success(function (response) {
                alert('----------------插入数据库成功------------------');
            }).error(function (response) {
                alert('----- ----------插入数据库失败------------------');
            });
        }


    };

}]);

