(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('song', {
            parent: 'entity',
            url: '/song',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Songs'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/song/songs.html',
                    controller: 'SongController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('song-detail', {
            parent: 'song',
            url: '/song/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Song'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/song/song-detail.html',
                    controller: 'SongDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Song', function($stateParams, Song) {
                    return Song.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'song',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('song-detail.edit', {
            parent: 'song-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/song/song-dialog.html',
                    controller: 'SongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Song', function(Song) {
                            return Song.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('song.new', {
            parent: 'song',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/song/song-dialog.html',
                    controller: 'SongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                songTitle: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('song', null, { reload: 'song' });
                }, function() {
                    $state.go('song');
                });
            }]
        })
        .state('song.edit', {
            parent: 'song',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/song/song-dialog.html',
                    controller: 'SongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Song', function(Song) {
                            return Song.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('song', null, { reload: 'song' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('song.delete', {
            parent: 'song',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/song/song-delete-dialog.html',
                    controller: 'SongDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Song', function(Song) {
                            return Song.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('song', null, { reload: 'song' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
