<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Board</title>
    
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/sample.css'/>"/>
    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 글 수정 화면 function */
        
        function fn_egov_select(uid) {
        	document.listForm.uid.value = uid;
           	document.listForm.action = "<c:url value='/view.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 등록 화면 function */
        function fn_egov_addView() {
           	document.listForm.action = "<c:url value='/addBoardView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
        	document.listForm.action = "<c:url value='/board.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/board.do'/>";
           	document.listForm.submit();
        }
        
    </script>
</head>
<body>
    <form id="listForm" name="listForm" method="post">
    <input type="hidden" name="uid" id="uid"/>
	<div id="content_pop">
       	<!-- 타이틀 -->
       	<div id="title">
       		<ul>
       			<li><img src="<c:url value='/images/title_dot.gif'/>" alt=""/>Board</li>
       		</ul>
       	</div>
		<!-- List -->
		<div id="table">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
				<caption style="visibility:hidden">카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블</caption>
				<colgroup>
					<col width="40"/>
					<col width="150"/>
					<col width="150"/>
					<col width="80"/>
					<col width="20"/>
					<col width="120"/>
				</colgroup>
				<tr>
					<th align="center">No</th>
					<th align="center">Subject</th>
					<th align="center">Content</th>
					<th align="center">Name</th>
					<th align="center">View</th>
					<th align="center">Date</th>
				</tr>
				<c:forEach var="result" items="${resultList}" varStatus="status">
	    			<tr>
	    				<td align="center" class="listtd"><c:out value="${(paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.pageSize + status.count)) + 1}"/></td>
	    				<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.brUid}"/>')"><c:out value="${result.brSubject}"/>&nbsp;</a></td>
	    				<td align="center" class="listtd"><c:out value="${result.brContent}"/>&nbsp;</td>
	    				<td align="center" class="listtd"><c:out value="${result.brName}"/>&nbsp;</td>
	    				<td align="center" class="listtd"><c:out value="${result.brViewcnt}"/>&nbsp;</td>
	    				<td align="center" class="listtd"><c:out value="${result.brRegdate}"/>&nbsp;</td>
	    			</tr>
				</c:forEach>
			</table>
		</div>
		<!-- /List -->
       	<div id="paging">
       		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
       		<input type="hidden" name="pageIndex" id="pageIndex"/>
       	</div>
       	<div id="sysbtn">
       		<ul>
      	    	<li>
          			<span class="btn_blue_l">
  	              		<a href="javascript:fn_egov_addView();">작성</a>
                    	<img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                	</span>
           		</li>
        	</ul>
       	</div>
	</div>
	</form>
</body>
</html>