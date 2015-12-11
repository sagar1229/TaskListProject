/**
 * Created by Jayavardhan on 12/9/15.
 */


'use strict';

angular.module('myApp').controller('UserController', ['$scope','UserService','$modal',function($scope, UserService,$modal) {
    var self = this;
    

    $scope.task_editable=false;
    $scope.taskList_editable=false;

    self.updateUser = function(user, id){
        UserService.updateUser(user, id)
            .then(
            function(d){
              $scope.user=d;
            },
            function(errResponse){
                console.error('Error while updating User.');
            }
        );
    };

    self.get  = function(id){
        UserService.getUser(id)
            .then(
            function(d) {
                $scope.user = d;
            },
            function(errResponse){
                console.error('Error  fetching user');
            }
        );

    };

    self.edit = function(id){
        console.log('id to be edited', id);
        UserService.getUser(id)
            .then(
            function(d) {
                $scope.user = d;
            },
            function(errResponse){
                console.error('Error  fetching user');
            }
        );
    };

    $scope.task={id:null,title:'',due_date:'',completed:false};
    $scope.taskLists=[];
    $scope.tasks=[];

    $scope.getTaskLists = function(id){
        UserService.getTaskLists(id)
            .then(
            function(d){
                $scope.taskLists = d;
            },
            function(errResponse){
                console.error('Error  fetching Tasklists');
            }
        );
        angular.forEach($scope.taskLists,function(tasklist){
           tasklist.tasks = $scope.getTasks(tasklist.id);
        });
    };


    $scope.getTasks = function(task_id){
        UserService.getTasks(task_id)
            .then(
            function(d){
                return d;
            },
            function(errResponse){
                console.error('Error  fetching Tasklists');
            }
        );

    };

    $scope.addTaskList = function(taskList){
        console.log($scope.user);
        UserService.addTaskList(taskList,$scope.user.id)
            .then(
            function(d){
                $scope.taskLists.push(d);
            },
            function(errResponse){
                console.error('Error  adding Tasklists');
            }

        );


    };

    $scope.addTask = function(task){
        UserService.addTask(task,taskList.id)
            .then(
            function(d){
                taskList.tasks.push(d);

            },
            function(errResponse){
                console.error('Error fetching Tasklists');
            }
        );
    };

    $scope.editTask = function(task){
        if(!$scope.task_editable){
            $('.editTask').text('done');
            $scope.task_editable=true;
        }else{
            $('.editTask').text('edit');
            $scope.task_editable=false;
        }

    };

    $scope.cancel = function(){
        $scope.task_editable=false;
        $('.editTask').text('edit');
    };

    $scope.editTaskList = function(taskList){
        if(!$scope.taskList_editable){
            $('.editTaskList').text('done');
            $scope.taskList_editable=true;
        }else{
            $('.editTaskList').text('edit');
            $scope.taskList_editable=false;
        }

    };

    $scope.cancelTaskList = function(){
        $('.editTaskList').text('edit');
        $scope.taskList_editable=false;
    };

    $scope.openTaskListModal = function(){
         $scope.modalopenTaskList = $modal.open({
             templateUrl:'/static/pages/modal_taskList.html' ,
             backdrop: 'static',
             keyboard:false,
             size:'100px',
             controller:'modalTaskListCtrl',
             scope: $scope
         });
    };

    $scope.openmodalTask = function(taskList){
        $scope.taskList=taskList;
        $scope.modalopenTask = $modal.open({
            templateUrl:'/static/pages/modal_task.html' ,
            backdrop: 'static',
            keyboard:false,
            size:'100px',
            controller:'modalTaskListCtrl',
            scope: $scope

        });

    };

}]);

angular.module('myApp').controller('modalTaskListCtrl',['$scope','$modalInstance',function($scope,$modalInstance){

    $scope.taskList={};
    $scope.task={};

    $scope.closeTaskListModal = function(){

        $modalInstance.close();
    };

    $scope.saveTaskListModal = function(){
        $scope.addTaskList($scope.taskList);
        $modalInstance.close();
    }

    $scope.closeTaskModal = function(){
        $modalInstance.close();
    }

    $scope.saveTaskModal = function(){
        $scope.addTask($scope.task)
        $modalInstance.close();
    }

}]);

