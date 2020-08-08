<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">

$(function(){
    //아이디 중복 확인 소스
    var memberIdCheck = $('.memberIdCheck');
    var memberId = $('.memberId');
    var memberIdComment = $('.memberIdComment');
    var memberPw = $('.memberPw');
    var memberPw2 = $('.memberPw2');
    var memberPw2Comment = $('.memberPw2Comment');

    var memberEmailCheck = $('.memberEmailCheck');
    var memberEmailAddress = $('.memberEmailAddress');
    var memberEmailAddressComment = $('.memberEmailAddressComment');
   
    var idCheck = $('.idCheck');
    var pwCheck2 = $('.pwCheck2');
    var eMailCheck = $('.eMailCheck');
    var emailOverlapCheck =$('.emailOverlapCheck');
    
    memberIdCheck.click(function(){
	$.ajax({
	    type: "POST",
	    url: './idchecker_1',
	    data: {memberId: memberId.val()},
	    dataType: "text",
 
	    success: function (json) {
			if(json == "good") {
		        memberIdComment.text('사용가능한 아이디 입니다.');	                   
	            idCheck.val('1');//1이 들어간 것 확인
	        }else{
	            memberIdComment.text('다른 아이디를 입력해 주세요.');
	            memberId.focus();
		   
	        }
	    },
 
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	})
    });
 
    memberEmailCheck.click(function(){

	console.log(memberEmailAddress.val());
	$.ajax({
	    type: 'post',
	    dataType: 'text',
	    url: './emailchecker_1',
	    data: {memberEmailAddress: memberEmailAddress.val()},
 
	    success: function (json) {
	        if(json == 'good') {
	            console.log(json.res);
		      memberEmailAddressComment.text('사용가능한 이메일 입니다.');       
	            emailOverlapCheck.val('1');//1이 들어간 것 확인
		   
	        }else{
	              memberEmailAddressComment.text('이미 사용중인 이메일입니다.');
	            memberEmailAddress.focus();
		   
	        }
	    },
 
	    error: function(){
	      console.log('failed');
 
	    }
	
	})
    });
    //비밀번호 동일 한지 체크
    memberPw2.keyup(function(){
       if(memberPw.val() == memberPw2.val()){
	   memberPw2Comment.text('');
	   memberPw2Comment.text('비밀번호가 일치합니다.');
	   memberPw2.css("background-color", "#B0F6AC");
	   pwCheck2.val('1');
       }else{
	   memberPw2Comment.text('');
	   memberPw2Comment.text('비밀번호가 일치하지 않습니다.');
	   memberPw2.css("background-color", "#FFCECE");
	   pwCheck2.val('0');
 
       }
    });
 
    //이메일 유효성 검사
    memberEmailAddress.keyup(function(){
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(regex.test(memberEmailAddress.val()) === false){
	    memberEmailAddress.css("background-color", "#FFCECE");
	    eMailCheck.val('0');
	}else{
	    memberEmailAddress.css("background-color", "#B0F6AC");
	    eMailCheck.val('1');
	}
    });
 
});
 
function checkSubmit(){
    //alert('checksubmit작동');
    //res변수는 register의 onsubmit에 반환해줄 값
    //check변수들은 어떤 것이 잘못됐는지 알려주는 변수
    var idCheck = $('.idCheck');
    var pwCheck2 = $('.pwCheck2');
    var eMailCheck = $('.eMailCheck');
    var emailOverlapCheck =$('.emailOverlapCheck');
 
    if(idCheck.val() == '1'){
	idcheckres = true;
	res = true;
    }else{
	idcheckres = false;
	res = false;
    }
    if(pwCheck2.val() == '1'){
	pwcheckres = true;
	res = true;
    }else if(pwCheck2.val() == '0'){
	pwcheckres = false;
	res = false;
    }
    if(eMailCheck.val() == '1'){
	emailcheckres =true;
	res = true;
    }else{
	emailcheckres = false;
	res = false;
    }
    if(emailOverlapCheck.val()=='1'){
	emailoverlapcheckres = true;
	res= true;
    }else{
	emailoverlapcheckres = false;
	res=false;

    }
    if(idcheckres == false){
	alert('중복체크를 해주세요');
	res=false;
    }else{
    	res = true;
    }
    if(emailOverlapCheck.val()== false){
	alert('이메일 중복체크를 해주세요');
	res=false;
    }else{
    	res=true;
    }
    return res;
    	
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>