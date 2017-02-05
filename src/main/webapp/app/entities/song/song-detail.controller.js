(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('SongDetailController', SongDetailController);

    SongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Song', 'Album'];

    function SongDetailController($scope, $rootScope, $stateParams, previousState, entity, Song, Album) {
        var vm = this;

        vm.song = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('cs499Assignment2App:songUpdate', function(event, result) {
            vm.song = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
