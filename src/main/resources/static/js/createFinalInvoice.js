
function pdf(){
	var pdfName = $('.invoiceNumber').html().replace(' ','');
	console.log(pdfName);
	var element = document.getElementById('invoice');
	var opt = {
			margin:       0,
			filename:     pdfName,
			html2canvas:  { scale: 10 },
			jsPDF:        { unit: 'mm', format: 'a4', orientation: 'portrait' }
		};

	// New Promise-based usage:
	html2pdf().set(opt).from(element).save();
}
    
function GoBackWithRefresh(event) {
	if ('referrer' in document) {
    	window.location = document.referrer;
   	}
	else {
    	window.history.back();
   	}
}
