<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("member.consultation.list")}[#if showPowered] - Powered By IGOMALL[/#if]</title>
<meta name="author" content="IGOMALL Team" />
<meta name="copyright" content="IGOMALL ++" />
[#include "/shop/include/reactjs.ftl" /]
<link href="${base}/resources/member/css/animate.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/member/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/member/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/babel" src="${base}/resources/member/js/withdraw/list.js"></script>
</head>
<body>
	[#assign current = "consultationList" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		<div class="row">
			[#include "/member/include/navigation.ftl" /]
			<div class="span10">
				<div class="input">
					<div class="title">${message("member.consultation.list")}</div>
					<div id="app"></div>
				</div>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
	
</body>
</html>