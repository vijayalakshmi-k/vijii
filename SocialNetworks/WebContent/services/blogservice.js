/**
 * BlogService
 */
app.factory('BlogService',function($http)

				{
					var blogService = {}
					blogService.addBlog = function(blog) {
						return $http.post("http://localhost:8091/ScocialNetworksMiddle/addblogpost",blog)
					}

					blogService.getBlogsWaitingForApproval = function() {
						return $http
								.get("http://localhost:8091/ScocialNetworksMiddle/getblogs/" + 0)
					}

					blogService.getBlogsApproved = function() {
						return $http.get("http://localhost:8091/ScocialNetworksMiddle/getblogs/" + 1)
					}

					blogService.getBlog = function(id) {
						return $http
								.get("http://localhost:8091/ScocialNetworksMiddle/getblog/"+ id)
					}

					blogService.hasUserLikedBlog = function(id) {
						return $http.get("http://localhost:8091/ScocialNetworksMiddle/hasUserLikedblog/"+id)
					}

					blogService.approve = function(blog) {  //blog.approved=0
						return $http.put("http://localhost:8091/ScocialNetworksMiddle/approve",blog)
					}
					blogService.reject = function(blog, rejectionReason) {
						return $http.put("http://localhost:8091/ScocialNetworksMiddle/reject/"+rejectionReason, blog)
					}

					blogService.updateLikes = function(id) {
						console.log('in blogservice,updateLikes:',id)
						return $http.put("http://localhost:8091/ScocialNetworksMiddle/updateLikes/"+id);
						// blog recored will get deleted
					}

					blogService.addComment = function(blogComment) {
						console.log('blogservice,addcomment:',blogComment)
						return $http.post("http://localhost:8091/ScocialNetworksMiddle/addComment",blogComment);
					}

					
					blogService.getBlogComments = function(id) {
						return $http.get("http://localhost:8091/ScocialNetworksMiddle/blogComments/"+id);
					}

					return blogService;

				})