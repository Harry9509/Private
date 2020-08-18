
var maxFileNum = 5;

var fileIndex = 0;

var fileCnt = 0;

var totalFileSize = 0;

var fileList = new Array();

var uploadSize = 50;

var maxUploadSize = 250;

$(document).ready(function(){
	fileIndex = $("#fileNum").val();
	fileCnt = fileIndex;
});

$(document).on("change", "input[name^='file']", function(e){
	if (fileCnt < 5) {
		fileName = e.target.files[0].name;
		addFileList(fileIndex, fileName);
		addFileInput(fileIndex);
		fileIndex++;
		fileCnt++;
	} else {
		alert("파일은 최대 5개 첨부할 수 있습니다.");
	}
});

$(document).on("click", ".deleteBtn", function(e) {
	var parent = e.target.parentElement.parentElement;
	var id = $(parent).attr('id');
	var index = id.split("_")[1];
	$("input[name='file_" + index + "']").remove();
	$("tr#fileList_" + index).remove();
	fileCnt--;
});

function addFileInput(fileIndex) {
	$("input[name='file_" + fileIndex + "']").hide();
	var input = "<input name='file_" + (Number(fileIndex) + 1) + "' class='fileUpload' type='file'/>";
	$("#fileInput").append(input);
}

function addFileList(fileIndex, fileName) {
	var html = "";
	html += "<tr id='fileList_" + fileIndex + "'>";
	html += "<td>" + fileName + "</td>";
	html += "<td>" + "<button type='button' class='deleteBtn'>Delete</button>" + "</td>";
	html += "</tr>";
	$("#fileTalbe").append(html);
}

function deleteFile(fileIndex) {
	$("#fileList_" + fileIndex).remove();
	fileIndex--;
}