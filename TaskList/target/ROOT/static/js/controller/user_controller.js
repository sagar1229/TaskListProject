/**
 * Created by Jayavardhan on 12/9/15.
 */


'use strict';

angular.module('myApp').controller('UserController', ['$scope','UserService','$modal',function($scope, UserService,$modal) {
    var self = this;
    $scope.task_editable=false;
    $scope.taskList_editable=false;
    $scope.user ={};

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
        $scope.getTaskLists(id);

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


    $scope.getTaskLists = function(id){
        UserService.getTaskLists(id)
            .then(
            function(d){
                $scope.taskLists = d;
                for(var index in $scope.taskLists){
                     $scope.taskLists[index].editable= false;
                     $scope.getTasks($scope.taskLists[index],id);
                }
            },
            function(errResponse){
                console.error('Error  fetching Tasklists');
            }
        );
    };


    $scope.getTasks = function(taskList,id){
        var taskList_id = taskList.id;
        UserService.getTasks(taskList_id,id)
            .then(
            function(d){
                taskList.tasks = d;
                for(var index in taskList.tasks){
                    taskList.tasks[index].editable= false;
                }
                return d;
            },
            function(errResponse){
                console.error('Error  fetching Tasklists');
            }
        );

    };

    $scope.addTaskList = function(taskList){
        UserService.addTaskList(taskList,$scope.user.id)
            .then(
            function(d){
                d.editable = false;
                $scope.taskLists.push(d);
            },
            function(errResponse){
                console.error('Error  adding Tasklists');
            }
        );


    };

    $scope.addTask = function(task){
        UserService.addTask(task,$scope.taskList.id,$scope.user.id)
            .then(
            function(d){
                d.editable=false;
                $scope.taskList.tasks.push(d);

            },
            function(errResponse){
                console.error('Error adding Task');
            }
        );
    };

    $scope.editTask = function(task,taskList){
        if(!task.editable){
            $scope.editingtask=angular.copy(task);
            $('.editTask').text('done');
            task.editable=true;
        }else{
            UserService.upDateTask($scope.editingtask,taskList.id,$scope.user.id)
                .then(
                function(d){
                    task=d;
                },
                function(errResponse){
                    console.error('Error in updating Task');
                }
            );
            task.editable=false;
            $('.editTask').text('edit');
        }
    };

    $scope.cancel = function(task){
        console.log($scope.editingtask);
        task.title = $scope.editingtask.title;
        task.completed=$scope.editingtask.completed;
        task.dueDate = $scope.editingtask.dueDate;
        task.editable=false;
        $('.editTask').text('edit');
    };

    $scope.editTaskList = function(taskList){
        if(!taskList.editable){
            $('.editTaskList').text('done');
            taskList.editable=true;
        }else{
            $('.editTaskList').text('edit');
            taskList.editable=false;
        }

    };

    $scope.cancelTaskList = function(taskList){
        $('.editTaskList').text('edit');
        taskList.editable=false;
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

