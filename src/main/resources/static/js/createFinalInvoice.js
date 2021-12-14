function pdf(){
        var element = document.getElementById('invoice');
var opt = {
  margin:       0,
  filename:     'myfile.pdf',
  html2canvas:  { scale: 3 },
  jsPDF:        { unit: 'mm', format: 'a4', orientation: 'portrait' }
};

// New Promise-based usage:
html2pdf().set(opt).from(element).save();


    }
    
    function GoBackWithRefresh(event) {
    	   if ('referrer' in document) {
    	      window.location = document.referrer;
    	      /* OR */
    	      //location.replace(document.referrer);
    	   } else {
    	      window.history.back();
    	   }
    	}