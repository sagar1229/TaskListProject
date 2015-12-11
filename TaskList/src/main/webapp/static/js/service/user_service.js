/**
 * Created by Jayavardhan on 12/9/15.
 */
'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    return {

        updateUser: function(user, id){
            return $http.put('http://localhost:8080/api/user/'+id, user)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while updating user');
                    return $q.reject(errResponse);
                }
            );
        },

        getUser: function(id){
            return $http.get('http://localhost:8080/api/user/'+id)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Retrieving from user');
                    return $q.reject(errResponse);
                }

            );
        },

        getTaskLists: function(user_id){
            return $http.get('http://localhost:8080/api/'+user_id+'/tasklists')
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Fetching TaskLists');
                }
            );
        },

        getTasks: function(taskList_id){
            return $http.get('http://localhost:8080/api/'+taskList_id+'/tasks')
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Fetching tasks');
                }
            );
        },

        addTaskList: function(taskList,user_id){
            return $http.post('http://localhost:8080/api/'+ user_id +'/taskLists/',taskList)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Creating taskList');
                    return $q.reject(errResponse);
                }
            );
        },

        addTask: function(tasks,taskList_id){
            return $http.post('http://localhost:8080/api/'+taskList_id+'tasks/',tasks)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Creating tasks');
                    return $q.reject(errResponse);
                }
            );
        }

    };

}]);

