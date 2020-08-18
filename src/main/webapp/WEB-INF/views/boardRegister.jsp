<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
  /**
  * @Class Name : egovSampleRegister.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
    	<c:if test="${type == 'create'}">Board 작성</c:if>
        <c:if test="${type == 'modify'}">Board 수정</c:if>
        <c:if test="${type == 'view'}"><c:out value="${boardVO.subject}"/></c:if>
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/sample.css'/>"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    
    <c:set var="length" value="${fn:length(boardVO.fileList)}"/>
    
    <script type="text/javaScript" language="javascript" defer="defer">        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
           	document.detailForm.action = "<c:url value='/board.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 수정 화면 function*/
        function fn_egov_modify() {
           	document.detailForm.action = "<c:url value='/updateView.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 삭제 function */
        function fn_egov_delete() {
           	document.detailForm.action = "<c:url value='/deleteBoard.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 등록 function */
        function fn_egov_save() {
        	frm = document.detailForm;
            frm.action = "<c:url value="${type == 'create' ? '/addBoard.do' : '/updateBoard.do'}"/>";
            frm.submit();
        }
        
    </script>
    <script type="text/javascript" src="<c:url value='/js/file/multiFile.js'/>" ></script>
    <!-- 여러 파일 업로드 -->
    
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

<form id="detailForm" name="detailForm" enctype="multipart/form-data" method="post">
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title">
    		<ul>
    			<li><img src="<c:url value='/images/title_dot.gif'/>" alt=""/>
                    <c:if test="${type == 'create'}">작성</c:if>
                    <c:if test="${type == 'modify'}">수정</c:if>
                    <c:if test="${type == 'view'}"><c:out value="${boardVO.subject}"/></c:if>
                </li>
    		</ul>
    	</div>
    	<!-- // 타이틀 -->
    	<div id="table">
    	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>
    		<c:if test="${type != 'create'}"> <!-- 만일 create 가 아니면,  -->
				<input type="hidden" name="uid" id="uid" value="${boardVO.uid }"/>
    		</c:if>
    		<c:if test="${type != 'create'}">
        		<tr>
        			<td class="tbtd_caption">
        				<label for="subject">subject</label>
        			</td>
        			<td class="tbtd_content">
        				<c:if test="${type == 'modify' }">
        					<input name="subject" id="subject" class="essentiality" maxlength="10" value="${boardVO.subject}"/>
        				</c:if>
        				
        				<c:if test="${type == 'view' }">
        					<c:out value="${boardVO.subject}"/>
        				</c:if>
        			</td>
        		</tr>
    		</c:if>
    		<c:if test="${type == 'create' }">
    		<tr>
    			<td class="tbtd_caption"><label for="subject">subject</label></td>
    			<td class="tbtd_content">
    				<input name="subject" id="subject" maxlength="30" class="txt"/>
    			</td>
    		</tr>
    		</c:if>
    		
    		
    		
    		
    		<c:if test="${type != 'create'}">
        		<tr>
        			<td class="tbtd_caption"><label for="name">name</label></td>
        			<td class="tbtd_content">
        				<c:if test="${type == 'modify' }"><c:out value="${boardVO.name}"/></c:if>
        				<c:if test="${type == 'view' }"><c:out value="${boardVO.name}"/></c:if>
        			</td>
        		</tr>
    		</c:if>
    		<c:if test="${type == 'create' }">
    		<tr>
    			<td class="tbtd_caption">
    				<label for="name">name</label>
    			</td>
    			<td class="tbtd_content">
    				<input name="name" id="name" maxlength="30" class="txt"/>
    			</td>
    		</tr>
    		</c:if>
    		
    		
    		
    		<c:if test="${type == 'create' }">
	    		<tr>
	    			<td class="tbtd_caption"><label for="password">password</label></td>
	    			<td class="tbtd_content">
						<input type="password" name="password" id="password" maxlength="30" class="txt"/>
	    			</td>
	    		</tr>
    		</c:if>
    		
    		
    		
    		<c:if test="${type != 'create' }">
	    		<tr>
	    			<td class="tbtd_caption">
		    			<label for="content">content</label>
		    		</td>
	    			<td class="tbtd_content">
	    				<c:if test="${type == 'modify' }">
	    					<textarea name="content" id="content" rows="5" cols="58" >
	    						<c:out value="${boardVO.content}"/>
	    					</textarea></c:if>
	    				<c:if test="${type == 'view' }">
	    					<textarea name="content" id="content" rows="5" cols="58" readonly="readonly">
	    						<c:out value="${boardVO.content}"/>
	    					</textarea>
	    				</c:if>
	                </td>
	    		</tr>
    		</c:if>
    		
    		<c:if test="${type == 'create' }">
    		<tr>
    			<td class="tbtd_caption">
    				<label for="content">content</label>
    			</td>
    			
    			<td class="tbtd_content">
    				<textarea name="content" id="content" rows="5" cols="58"></textarea>
                </td>
    		</tr>
    		</c:if>
    		
    		
    		
    		<c:if test="${type == 'view' }">
  			<tr>
    			<td class="tbtd_caption">
    				<label for="file">files</label>
    			</td>
    			<td class="tbtd_content">
    				<c:forEach var="file" items="${boardVO.fileList}" varStatus="status">
						<div>
							<a href="${pageContext.request.contextPath}/file/download.do?br_uid=${boardVO.uid}&f_uid=${file.f_uid}">
								<c:out value="${file.originalName }"/>
							</a>
						</div>
					</c:forEach>
                </td>
    		</tr>
    		</c:if>
    		
    		
    		
    		<c:if test="${type == 'create' }">
  		    <tr>
    			<td class="tbtd_caption">
    				<label for="file">files</label>
    			</td>
    			<td class="tbtd_content">
    				<div id="fileInput">
    					<input name="file_0" id="fileUpload" type="file" />
    				</div>
    				<table id="fileTalbe">
    				</table>
                </td>
    		</tr>
    		</c:if>
    		
    		
    		<c:if test="${type == 'modify'}">
    		<tr>
    			<td class="tbtd_caption"><label for="file">files</label></td>
    			<td class="tbtd_content">
    				<div id="fileInput"><input name="file_${length}" id="fileUpload" type="file" /></div>
    				<table id="fileTalbe">
    					<c:forEach var="file" items="${boardVO.fileList}" varStatus="status">
    						<tr id="fileList_${status.count - 1}">
    							<td><c:out value="${file.originalName }"/></td>
    							<td><button type="button" class="deleteBtn">Delete</button></td>
    							<td><input type="hidden" name="fileUids" value="${file.f_uid }"></input></td>
    						</tr>
    					</c:forEach>
    				</table>
                </td>
    		</tr>
    		</c:if>
    		
    		<!-- 파일  업로드 -->
    		<tr><td><input type="hidden" id="fileNum" value="${length}"/></td></tr>
    	</table>
      	</div>	
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList();">목록</a>
                        <img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<li>
    				<c:if test="${type != 'view' }">
	                    <span class="btn_blue_l">
	                        <a href="javascript:fn_egov_save();">
	                            <c:if test="${type == 'create'}">작성</c:if>
	                            <c:if test="${type == 'modify'}">수정</c:if>
	                        </a>
	                        <img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                    </span>
                    </c:if>
                    <c:if test="${type == 'view' }">
	                    <span class="btn_blue_l">
	                    	<a href="javascript:fn_egov_modify()">수정</a>
	                        <img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                    </span>
                    </c:if>
                </li>
    			<c:if test="${type != 'create'}">
                    <li>
                        <span class="btn_blue_l">
                            <a href="javascript:fn_egov_delete();">삭제</a>
                            <img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                        </span>
                    </li>
    			</c:if>
    			<c:if test="${type != 'view' }">
	    			<li>
	                    <span class="btn_blue_l">
	                        <a href="javascript:document.detailForm.reset();">초기화</a>
	                        <img src="<c:url value='/images/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                    </span>
	                </li>
                </c:if>
            </ul>
    	</div>
    </div>
    <!-- 
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${pageIndex}'/>"/>
	 -->
</form>

</body>  
</html>