/**
 * Created by Jayavardhan on 12/9/15.
 */
'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    return {

        updateUser: function(user, id){
            return $http.put('/api/user/'+id, user)
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
            return $http.get('/api/user/'+id)
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
            return $http.get('/api/user/'+user_id+'/taskLists')
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Fetching TaskLists');
                }
            );
        },

        getTasks: function(taskList_id,user_id){
            return $http.get('/api/user/'+user_id+'/taskLists/'+taskList_id+'/tasks')
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
            return $http.post('/api/user/'+ user_id +'/taskLists',taskList)
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

        addTask: function(task,taskList_id,user_id){
            return $http.post('/api/user/'+user_id+'/taskLists/'+taskList_id+'/tasks',task)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Creating tasks');
                    return $q.reject(errResponse);
                }
            );
        },

        upDateTask: function(task,taskList_id,user_id){
            return $http.put('/api/user/'+user_id+'/taskLists/'+taskList_id+'/tasks/'+task.id,task)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error Updating tasks');
                    return $q.reject(errResponse);
                }
            );
        }

    };

}]);

