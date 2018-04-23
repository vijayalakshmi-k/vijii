/**
 * #/getblog/761
 */
app.controller('BlogDetailsCtrl', function($scope, $rootScope, $location,BlogService,$sce,$routeParams) {
	var id = $routeParams.id;
	$scope.rejectionTxt=false;
	$scope.showComments=false;

	BlogService.getBlog(id)
	.then(function(response) {
		console.log('in blog details controller..')
		console.log(response.data)
		$scope.blog = response.data
		$scope.content = $sce.trustAsHtml($scope.blog.blogContent)
	}, function(response) 
	{
		$rootScope.error = response.data
		if (response.status == 401)
			$location.path('/login')
	})

	BlogService.hasUserLikedBlog(id).
	then(function(response) {
		// response.data wii be either null or an object of type
		// BlogPostLikes
		console.log('likes updated in blogdetails')
		if (response.data == '')    //blogs is not yet liked
			$scope.isLiked = false  //determine the color of the glyphicon
		else
			$scope.isLiked = true

	}, function(response) {
		$rootScope.error = response.data
		if (response.status == 401)
			$location.path('/login')

	})

	$scope.approve = function(blog) {
		console.log('blogdetailscontroller,approve:')
		console.log(blog)
   BlogService.approve(blog).then(function(response) {
      $location.path('/blogsnotapproved')
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')

		})
	}

	$scope.reject = function(blog) {
		BlogService.reject(blog, $scope.rejectionReason).then(
				function(response) {
					$location.path('/blogsnotapproved')
				}, function(response) {
					$rootScope.error = response.data
					if (response.status == 401)
						$location.path('/login')

				})
	}
	$scope.showRejectionTxt = function() {
		$scope.rejectionTxt = true;
	}

	$scope.updateLikes = function(id) {
		//console.log('in blogdetailscontroller:' ,likes)
		console.log('in blogdetailscontroller')
     BlogService.updateLikes(id).then(function(response) {
			$scope.blog = response.data // updated blogpost Likes
			$scope.isLiked = !$scope.isLiked
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')
		})
	}

	$scope.addComment = function(blog,commentTxt) {
		$scope.blogComment = {}
		alert('comment is added successfully...,')
      console.log('in blogdetailscontroller:' ,commentTxt)

		// blogcomment.setBlogPost(blogpost) in middleware
		$scope.blogComment.blogPost = blog;
        // blogcomment.setBlogcomment(blogcomment) in middleware
		$scope.blogComment.commentTxt = commentTxt;
		BlogService.addComment($scope.blogComment).then(function(response) {
			$scope.commentTxt = ''
			getBlogComments(id)
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')
			else {
				$scope.exceptionMessage = response.data
			}
		})
	}

	function getBlogComments(id) {

		BlogService.getBlogComments(id).then(function(response) {    //getBlogComments
			$scope.comments = response.data
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')
		})
		$scope.onShowComments=function(){
			$scope.showComments=!$scope.showComments;
		}

	}
	getBlogComments(id)
})
