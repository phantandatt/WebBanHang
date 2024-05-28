<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sản phẩm</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<!-- menubar Start -->
	<jsp:include page="menu.jsp"></jsp:include>
	<!-- menubar End -->

	<!--bat dau duong dan-->
	<div class="container-fluid">
		<div class="row px-xl-5 py-4">
			<div class="col-12">
				<nav class="breadcrumb bg-light mb-30">
					<a class="breadcrumb-item text-dark text text-decoration-none"
						href="home">Trang chủ</a> <a
						class="breadcrumb-item text-dark text-decoration-none" href="shop">Danh
						mục</a> <span class="breadcrumb-item active text-decoration-none">${text}</span>
				</nav>
			</div>
		</div>
	</div>
	<!-- ket thuc duong dan -->


	<!-- San pham -->
	<div class="container-fluid">
		<div class="row px-xl-5">
			<!-- bo loc san pham start -->
			<div class="col-lg-3 col-md-4">

				<!-- loc theo loai start -->
				<h5 class="section-title position-relative text-uppercase mb-3">
					<span class="bg-secondary pr-3">Lọc thương hiệu</span>
				</h5>
				<div class="bg-light p-4 mb-30">
					<form>
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<a class="" style="text-decoration: none;" href="shop">All
								Category</a> <span class="badge border font-weight-normal">${totalProduct}</span>
						</div>
						<c:forEach items="${ListCategory}" var="category">
							<div onclick="load()"
								class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
								<input id="CategoryID" type="hidden"
									value="${category.getIdCategory()}"> <a
									style="text-decoration: none;" class="text-dark"
									href="shop?action=category&CategoryID=${category.getIdCategory()}">${category.getNameCategory()}</a>
								<span class="badge border font-weight-normal">${AmountProductCate.getAmountProdByID(category.getIdCategory())}</span>
							</div>
						</c:forEach>
					</form>
				</div>
				<!-- loc theo loai End -->


				<!-- loc theo color Start -->
				<!-- loc theo color End -->


			</div>
			<!--bo loc san pham End -->


			<!-- hien thi san pham Start -->
			<div class="col-lg-9 col-md-8">
				<div class="row pb-3">
					<div class="col-12 pb-1">
						<!-- bo loc -->
						<div
							class="d-flex align-items-center justify-content-between mb-4">
							<div></div>
							<div class="ml-2">
								<!--<div class="btn-group">
									<button type="button"
										class="btn btn-sm btn-light dropdown-toggle"
										data-toggle="dropdown">Sorting</button>
									<div class="dropdown-menu dropdown-menu-right">
										<a class="dropdown-item" href="#">Latest</a> <a
											class="dropdown-item" href="#">Popularity</a> <a
											class="dropdown-item" href="#">Best Rating</a>
									</div>
								</div>
								<div class="btn-group ml-2">
									<button type="button"
										class="btn btn-sm btn-light dropdown-toggle"
										data-toggle="dropdown">Showing</button>
									<div class="dropdown-menu dropdown-menu-right">
										<a class="dropdown-item" href="#">10</a> <a
											class="dropdown-item" href="#">20</a> <a
											class="dropdown-item" href="#">30</a>
									</div>
								</div>  -->

							</div>
						</div>
						<!--ket thuc bo loc -->
					</div>
					<!--cho nay hien thi san pham -->
					<div id="content" class="row">

						<c:forEach items="${ListProduct}" var="product">

							<div class="col-lg-4 col-md-6 col-sm-6 pb-1">
								<div class="product-item bg-light mb-4">
									<div class="product-img position-relative overflow-hidden py-4">
										<img class="img-fluid w-100" src="img/${product.getImgPath()}"
											alt="">
										<div class="product-action">
											<a class="btn btn-outline-dark btn-square"
												href="addtocart?ProductID=${product.getProductID()}&soluong=1"><i
												class="fa fa-shopping-cart"></i></a> <a
												class="btn btn-outline-dark btn-square"
												href="detail?ProductID=${product.getProductID()}"><i
												class="fa fa-search"></i></a>
										</div>
									</div>
									<div class="text-center py-4">
										<a class="h5 text-decoration-none text-truncate"
											href="detail?ProductID=${product.getProductID()}">${product.getProductName()}</a>
										<div
											class="d-flex align-items-center justify-content-center mt-2">
											<h5>${product.getUnit_price()}${product.getPrice()}</h5>
											<h6 class="text-muted ml-2">
												<del>${product.getUnit_price()}${product.getPrice()}</del>
											</h6>
										</div>
										<div
											class="d-flex align-items-center justify-content-center mb-1">
											<p class="text-dark">${rvDAO.avgTotalStarReviews(product.getProductID())}
												<i class="fa fa-star text-primary mr-1"></i>(${rvDAO.totalReviewsOfProductID(product.getProductID())}
												Reviews)
											</p>

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<!-- hien thi san pham End -->
		</div>
	</div>
	<!-- san pham End -->
	<!-- Footer Start -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>



	<!-- search su dung ajax -->
	<script>
		function searchByName(param) {
			var txtSearch = param.value;
			$.ajax({

				url : "/PhoneStore/SearchByAjax",
				type : "get",
				data : {
					search : txtSearch
				},
				success : function(data) {
					var row = document.getElementById("content");
					row.innerHTML = data;
				},
				error : function(xhr) {

				}

			});

		}
		
	</script>

	<!-- Contact Javascript File -->
	<script src="mail/jqBootstrapValidation.min.js"></script>
	<script src="mail/contact.js"></script>

	<!-- Template Javascript -->
	<script src="js/main.js"></script>
</body>


</html>