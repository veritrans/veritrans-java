(function() {
  $(function() {
    var callback, card, closeDialog, openDialog;
    Veritrans.url = "https://api.sandbox.veritrans.co.id/v2/token";
    Veritrans.client_key = "VT-client-L0ixAGIFOyskZpAi";
    card = function() {
      return {
        card_number: $("#order_payment_card_number").val().replace(/[^0-9]/g, ''),
        card_exp_month: $("#order_payment_card_exp_month").val(),
        card_exp_year: $("#order_payment_card_exp_year").val(),
        card_cvv: $("#order_payment_card_cvv").val(),
        secure: true,
        bank: 'bni',
        gross_amount: $("#gross_amount").val()
      };
    };
    $("#submit-button").click(function(event) {
      event.preventDefault();
      $(this).attr("disabled", "disabled");
      Veritrans.token(card, callback);
      $("#order_payment_card_number").val().replace(/[^0-9]/g, '');
      return false;
    });
    callback = function(response) {
      var html;
      if (response.redirect_url) {
        console.log("Open Dialog 3D Secure");
        return openDialog(response.redirect_url);
      } else if (response.status_code === "200") {
        closeDialog();
        $("#order_payment_vt_token").val(response.token_id);
        console.log("token id is " + response.token_id);
        console.log("submitting the form....");
        return $("#new_order_payment").submit();
      } else {
        closeDialog();
        html = "<div class=\"alert alert-error\"><p>Please validate your card number, cvv or use another card example number</p></div>";
        $("#notif-app .span12").append(html);
        $("#submit-button").removeAttr("disabled");
        return console.log(JSON.stringify(response));
      }
    };
    openDialog = function(url) {
      return $.fancybox.open({
        href: url,
        type: 'iframe',
        autoSize: false,
        width: 400,
        height: 420,
        closeBtn: true,
        modal: true
      });
    };
    return closeDialog = function() {
      return $.fancybox.close();
    };
  });

}).call(this);
