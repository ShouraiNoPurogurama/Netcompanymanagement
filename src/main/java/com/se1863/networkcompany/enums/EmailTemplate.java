package com.se1863.networkcompany.enums;

public enum EmailTemplate {
    ACCOUNT_BLOCKED(
            """
                    <!--
                    * This email was built using Tabular.
                    * For more information, visit https://tabular.email
                    -->
                    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                    <html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" lang="en">
                    <head>
                    <title></title>
                    <meta charset="UTF-8" />
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    <!--[if !mso]><!-->
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <!--<![endif]-->
                    <meta name="x-apple-disable-message-reformatting" content="" />
                    <meta content="target-densitydpi=device-dpi" name="viewport" />
                    <meta content="true" name="HandheldFriendly" />
                    <meta content="width=device-width" name="viewport" />
                    <meta name="format-detection" content="telephone=no, date=no, address=no, email=no, url=no" />
                    <style type="text/css">
                    table {
                    border-collapse: separate;
                    table-layout: fixed;
                    mso-table-lspace: 0pt;
                    mso-table-rspace: 0pt
                    }
                    table td {
                    border-collapse: collapse
                    }
                    .ExternalClass {
                    width: 100%
                    }
                    .ExternalClass,
                    .ExternalClass p,
                    .ExternalClass span,
                    .ExternalClass font,
                    .ExternalClass td,
                    .ExternalClass div {
                    line-height: 100%
                    }
                    body, a, li, p, h1, h2, h3 {
                    -ms-text-size-adjust: 100%;
                    -webkit-text-size-adjust: 100%;
                    }
                    html {
                    -webkit-text-size-adjust: none !important
                    }
                    body, #innerTable {
                    -webkit-font-smoothing: antialiased;
                    -moz-osx-font-smoothing: grayscale
                    }
                    #innerTable img+div {
                    display: none;
                    display: none !important
                    }
                    img {
                    Margin: 0;
                    padding: 0;
                    -ms-interpolation-mode: bicubic
                    }
                    h1, h2, h3, p, a {
                    line-height: 1;
                    overflow-wrap: normal;
                    white-space: normal;
                    word-break: break-word
                    }
                    a {
                    text-decoration: none
                    }
                    h1, h2, h3, p {
                    min-width: 100%!important;
                    width: 100%!important;
                    max-width: 100%!important;
                    display: inline-block!important;
                    border: 0;
                    padding: 0;
                    margin: 0
                    }
                    a[x-apple-data-detectors] {
                    color: inherit !important;
                    text-decoration: none !important;
                    font-size: inherit !important;
                    font-family: inherit !important;
                    font-weight: inherit !important;
                    line-height: inherit !important
                    }
                    a[href^="mailto"],
                    a[href^="tel"],
                    a[href^="sms"] {
                    color: inherit;
                    text-decoration: none
                    }
                    img,p{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:26px;font-weight:400;font-style:normal;font-size:16px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#9095a2;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px}h1{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:52px;font-weight:700;font-style:normal;font-size:48px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#000;text-align:left;mso-line-height-rule:exactly;mso-text-raise:1px}h2{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:30px;font-weight:700;font-style:normal;font-size:24px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}h3{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:26px;font-weight:700;font-style:normal;font-size:20px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}
                    </style>
                    <style type="text/css">
                    @media (min-width: 481px) {
                    .hd { display: none!important }
                    }
                    </style>
                    <style type="text/css">
                    @media (max-width: 480px) {
                    .hm { display: none!important }
                    }
                    </style>
                    <style type="text/css">
                    [style*="Fira Sans"] {font-family: 'Fira Sans', BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif !important;} [style*="Montserrat"] {font-family: 'Montserrat', BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif !important;}
                    @media only screen and (min-width: 481px) {img,p{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:28px;font-weight:400;font-style:normal;font-size:18px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#9095a2;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px}h1{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:52px;font-weight:700;font-style:normal;font-size:48px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#000;text-align:left;mso-line-height-rule:exactly;mso-text-raise:1px}h2{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:30px;font-weight:700;font-style:normal;font-size:24px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}h3{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:26px;font-weight:700;font-style:normal;font-size:20px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}.t9{padding-bottom:100px!important}.t11{padding-bottom:100px!important;width:620px!important}.t22{mso-line-height-alt:41px!important;line-height:41px!important}.t24{width:72px!important}.t32{mso-line-height-alt:0px!important;line-height:0!important;display:none!important}.t34,.t39{padding-bottom:21px!important}.t40{line-height:52px!important;font-size:48px!important;mso-text-raise:1px!important}.t42{mso-line-height-alt:28px!important;line-height:28px!important}.t50{line-height:28px!important;font-size:18px!important}.t52{mso-line-height-alt:25px!important;line-height:25px!important}.t60{line-height:28px!important;font-size:18px!important}.t67{padding-top:79px!important;padding-bottom:80px!important}.t69{padding-top:79px!important;padding-bottom:80px!important;width:620px!important}.t80{mso-line-height-alt:27px!important;line-height:27px!important}.t88{line-height:28px!important;font-size:18px!important}.t102{width:483px!important}.t110{mso-line-height-alt:0px!important;line-height:0!important;display:none!important}.t112{padding-bottom:21px!important;width:600px!important}.t117{padding-bottom:21px!important}.t120{mso-line-height-alt:12px!important;line-height:12px!important}.t122{padding-bottom:21px!important;width:600px!important}.t127{padding-bottom:21px!important}.t130{mso-line-height-alt:6px!important;line-height:6px!important}.t132,.t137{padding-bottom:21px!important}.t138{line-height:52px!important;font-size:48px!important;mso-text-raise:1px!important}.t142,.t147{line-height:48px!important;mso-text-raise:11px!important}.t148{line-height:48px!important;font-size:13px!important;mso-text-raise:11px!important}.t150{line-height:48px!important;mso-text-raise:11px!important}}
                    </style>
                    <style type="text/css" media="screen and (min-width:481px)">.moz-text-html img,.moz-text-html p{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:28px;font-weight:400;font-style:normal;font-size:18px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#9095a2;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px}.moz-text-html h1{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:52px;font-weight:700;font-style:normal;font-size:48px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#000;text-align:left;mso-line-height-rule:exactly;mso-text-raise:1px}.moz-text-html h2{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:30px;font-weight:700;font-style:normal;font-size:24px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}.moz-text-html h3{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:26px;font-weight:700;font-style:normal;font-size:20px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}.moz-text-html .t9{padding-bottom:100px!important}.moz-text-html .t11{padding-bottom:100px!important;width:620px!important}.moz-text-html .t22{mso-line-height-alt:41px!important;line-height:41px!important}.moz-text-html .t24{width:72px!important}.moz-text-html .t32{mso-line-height-alt:0px!important;line-height:0!important;display:none!important}.moz-text-html .t34,.moz-text-html .t39{padding-bottom:21px!important}.moz-text-html .t40{line-height:52px!important;font-size:48px!important;mso-text-raise:1px!important}.moz-text-html .t42{mso-line-height-alt:28px!important;line-height:28px!important}.moz-text-html .t50{line-height:28px!important;font-size:18px!important}.moz-text-html .t52{mso-line-height-alt:25px!important;line-height:25px!important}.moz-text-html .t60{line-height:28px!important;font-size:18px!important}.moz-text-html .t67{padding-top:79px!important;padding-bottom:80px!important}.moz-text-html .t69{padding-top:79px!important;padding-bottom:80px!important;width:620px!important}.moz-text-html .t80{mso-line-height-alt:27px!important;line-height:27px!important}.moz-text-html .t88{line-height:28px!important;font-size:18px!important}.moz-text-html .t102{width:483px!important}.moz-text-html .t110{mso-line-height-alt:0px!important;line-height:0!important;display:none!important}.moz-text-html .t112{padding-bottom:21px!important;width:600px!important}.moz-text-html .t117{padding-bottom:21px!important}.moz-text-html .t120{mso-line-height-alt:12px!important;line-height:12px!important}.moz-text-html .t122{padding-bottom:21px!important;width:600px!important}.moz-text-html .t127{padding-bottom:21px!important}.moz-text-html .t130{mso-line-height-alt:6px!important;line-height:6px!important}.moz-text-html .t132,.moz-text-html .t137{padding-bottom:21px!important}.moz-text-html .t138{line-height:52px!important;font-size:48px!important;mso-text-raise:1px!important}.moz-text-html .t142,.moz-text-html .t147{line-height:48px!important;mso-text-raise:11px!important}.moz-text-html .t148{line-height:48px!important;font-size:13px!important;mso-text-raise:11px!important}.moz-text-html .t150{line-height:48px!important;mso-text-raise:11px!important}</style>
                    <!--[if !mso]><!-->
                    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@400;600;700&amp;family=Montserrat:wght@800&amp;display=swap" rel="stylesheet" type="text/css" />
                    <!--<![endif]-->
                    <!--[if mso]>
                    <style type="text/css">
                    img,p{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:28px;font-weight:400;font-style:normal;font-size:18px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#9095a2;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px}h1{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:52px;font-weight:700;font-style:normal;font-size:48px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#000;text-align:left;mso-line-height-rule:exactly;mso-text-raise:1px}h2{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:30px;font-weight:700;font-style:normal;font-size:24px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}h3{margin:0;Margin:0;font-family:Fira Sans,BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif;line-height:26px;font-weight:700;font-style:normal;font-size:20px;text-decoration:none;text-transform:none;letter-spacing:0;direction:ltr;color:#333;text-align:left;mso-line-height-rule:exactly;mso-text-raise:2px}td.t9{padding-bottom:100px !important}td.t11{padding-bottom:100px !important;width:680px !important}div.t22{mso-line-height-alt:41px !important;line-height:41px !important}td.t24{width:72px !important}div.t32{mso-line-height-alt:0px !important;line-height:0 !important;display:none !important}td.t34,td.t39{padding-bottom:21px !important}h1.t40{line-height:52px !important;font-size:48px !important;mso-text-raise:1px !important}div.t42{mso-line-height-alt:28px !important;line-height:28px !important}p.t50{line-height:28px !important;font-size:18px !important}div.t52{mso-line-height-alt:25px !important;line-height:25px !important}p.t60{line-height:28px !important;font-size:18px !important}td.t67{padding-top:79px !important;padding-bottom:80px !important}td.t69{padding-top:79px !important;padding-bottom:80px !important;width:680px !important}div.t80{mso-line-height-alt:27px !important;line-height:27px !important}p.t88{line-height:28px !important;font-size:18px !important}td.t102{width:483px !important}div.t110{mso-line-height-alt:0px !important;line-height:0 !important;display:none !important}td.t112{padding-bottom:21px !important;width:600px !important}td.t117{padding-bottom:21px !important}div.t120{mso-line-height-alt:12px !important;line-height:12px !important}td.t122{padding-bottom:21px !important;width:600px !important}td.t127{padding-bottom:21px !important}div.t130{mso-line-height-alt:6px !important;line-height:6px !important}td.t132,td.t137{padding-bottom:21px !important}h1.t138{line-height:52px !important;font-size:48px !important;mso-text-raise:1px !important}td.t142,td.t147{line-height:48px !important;mso-text-raise:11px !important}a.t148{line-height:48px !important;font-size:13px !important;mso-text-raise:11px !important}td.t150{line-height:48px !important;mso-text-raise:11px !important}
                    </style>
                    <![endif]-->
                    <!--[if mso]>
                    <xml>
                    <o:OfficeDocumentSettings>
                    <o:AllowPNG/>
                    <o:PixelsPerInch>96</o:PixelsPerInch>
                    </o:OfficeDocumentSettings>
                    </xml>
                    <![endif]-->
                    </head>
                    <body class="t0" style="min-width:100%;Margin:0px;padding:0px;background-color:#EDEDED;"><div class="t1" style="background-color:#EDEDED;"><table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" align="center"><tr><td class="t2" style="font-size:0;line-height:0;mso-line-height-rule:exactly;background-color:#EDEDED;" valign="top" align="center">
                    <!--[if mso]>
                    <v:background xmlns:v="urn:schemas-microsoft-com:vml" fill="true" stroke="false">
                    <v:fill color="#EDEDED"/>
                    </v:background>
                    <![endif]-->
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" align="center" id="innerTable"><tr><td>
                    <table class="t10" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t11" style="background-color:#FFFFFF;width:420px;padding:60px 30px 70px 30px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t11" style="background-color:#FFFFFF;width:480px;padding:60px 30px 70px 30px;"><![endif]-->
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0"><tr><td>
                    <table class="t19" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t20" style="width:475px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t20" style="width:475px;"><![endif]-->
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0"><tr><td>
                    <table class="t23" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t24" style="width:40px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t24" style="width:40px;"><![endif]-->
                    <div style="font-size:0px;"><img class="t30" style="display:block;border:0;height:auto;width:100%;Margin:0;max-width:100%;" width="72" height="72" alt="" src="https://24c56d71-4566-4e09-8a5c-e32935acc742.b-cdn.net/e/e4d7c9b4-16d0-4e0f-a854-e7330435bd27/9b1d110c-6fe0-419e-a30b-bb77519bfd19.png"/></div></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t22" style="mso-line-height-rule:exactly;mso-line-height-alt:50px;line-height:50px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t33" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t34" style="width:475px;padding:0 0 30px 0;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t34" style="width:475px;padding:0 0 30px 0;"><![endif]-->
                    <h1 class="t40" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:38px;font-weight:700;font-style:normal;font-size:28px;text-decoration:none;text-transform:none;direction:ltr;color:#212121;text-align:center;mso-line-height-rule:exactly;mso-text-raise:3px;">Your Account</h1></td>
                    </tr></table>
                    </td></tr>
                    <!--[if !mso]><!--><tr><td><div class="t32" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr>
                    <!--<![endif]-->
                    <tr><td>
                    <table class="t131" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t132" style="width:475px;padding:0 0 0 0;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t132" style="width:475px;padding:0 0 30px 0;"><![endif]-->
                    <h1 class="t138" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:38px;font-weight:700;font-style:normal;font-size:28px;text-decoration:none;text-transform:none;direction:ltr;color:#212121;text-align:center;mso-line-height-rule:exactly;mso-text-raise:3px;">Has Been Blocked</h1></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t130" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t43" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t44" style="width:475px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t44" style="width:475px;"><![endif]-->
                    <p class="t50" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:26px;font-weight:400;font-style:normal;font-size:16px;text-decoration:none;text-transform:none;direction:ltr;color:#4D4F54;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px;">Dear Customer,</p></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t42" style="mso-line-height-rule:exactly;mso-line-height-alt:18px;line-height:18px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t53" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t54" style="width:475px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t54" style="width:475px;"><![endif]-->
                    <p class="t60" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:26px;font-weight:400;font-style:normal;font-size:16px;text-decoration:none;text-transform:none;direction:ltr;color:#4D4F54;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px;">We regret to inform you that your account has been blocked due to suspicious activity or violation of our terms of service.</p></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t52" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t81" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t82" style="width:475px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t82" style="width:475px;"><![endif]-->
                    <p class="t88" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:26px;font-weight:400;font-style:normal;font-size:16px;text-decoration:none;text-transform:none;direction:ltr;color:#4D4F54;text-align:left;mso-line-height-rule:exactly;mso-text-raise:3px;">Please contact our support team for further assistance.</p></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t80" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr></table></td>
                    </tr></table>
                    </td></tr><tr><td>
                    <table class="t141" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t142" style="background-color:#40CF9A;overflow:hidden;width:246px;text-align:center;line-height:46px;mso-line-height-rule:exactly;mso-text-raise:10px;border-radius:40px 40px 40px 40px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t142" style="background-color:#40CF9A;overflow:hidden;width:246px;text-align:center;line-height:46px;mso-line-height-rule:exactly;mso-text-raise:10px;border-radius:40px 40px 40px 40px;"><![endif]-->
                    <a class="t148" href="https://tabular.email" style="display:block;margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Montserrat';line-height:46px;font-weight:800;font-style:normal;font-size:12px;text-decoration:none;text-transform:uppercase;letter-spacing:0.5px;direction:ltr;color:#FFFFFF;text-align:center;mso-line-height-rule:exactly;mso-text-raise:10px;" target="_blank">Contact Support</a></td>
                    </tr></table>
                    </td></tr></table></td>
                    </tr></table>
                    </td></tr><tr><td>
                    <table class="t68" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t69" style="background-color:#000000;width:420px;padding:60px 30px 60px 30px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t69" style="background-color:#000000;width:480px;padding:60px 30px 60px 30px;"><![endif]-->
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0"><tr><td>
                    <table class="t111" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t112" style="width:480px;padding:0 0 40px 0;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t112" style="width:480px;padding:0 0 40px 0;"><![endif]-->
                    <h1 class="t118" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:32px;font-weight:600;font-style:normal;font-size:32px;text-decoration:none;text-transform:none;direction:ltr;color:#FFFFFF;text-align:center;mso-line-height-rule:exactly;">#SE1863</h1></td>
                    </tr></table>
                    </td></tr>
                    <!--[if !mso]><!--><tr><td><div class="t110" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr>
                    <!--<![endif]-->
                    <tr><td>
                    <table class="t121" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t122" style="width:480px;padding:0 0 40px 0;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t122" style="width:480px;padding:0 0 40px 0;"><![endif]-->
                    <h1 class="t128" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:32px;font-weight:600;font-style:normal;font-size:32px;text-decoration:none;text-transform:none;direction:ltr;color:#FFFFFF;text-align:center;mso-line-height-rule:exactly;">Network Company</h1></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t120" style="mso-line-height-rule:exactly;mso-line-height-alt:30px;line-height:30px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t77" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t78" style="width:475px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t78" style="width:475px;"><![endif]-->
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0"><tr><td>
                    <table class="t91" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t92" style="width:474px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t92" style="width:474px;"><![endif]-->
                    <p class="t98" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:22px;font-weight:400;font-style:normal;font-size:14px;text-decoration:none;text-transform:none;direction:ltr;color:#9095A2;text-align:center;mso-line-height-rule:exactly;mso-text-raise:2px;">    Thank you,     </p></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t90" style="mso-line-height-rule:exactly;mso-line-height-alt:11px;line-height:11px;font-size:1px;display:block;">&nbsp;</div></td></tr><tr><td>
                    <table class="t101" role="presentation" cellpadding="0" cellspacing="0" align="center"><tr>
                    <!--[if !mso]><!--><td class="t102" style="width:480px;">
                    <!--<![endif]-->
                    <!--[if mso]><td class="t102" style="width:480px;"><![endif]-->
                    <p class="t108" style="margin:0;Margin:0;font-family:BlinkMacSystemFont,Segoe UI,Helvetica Neue,Arial,sans-serif,'Fira Sans';line-height:22px;font-weight:400;font-style:normal;font-size:14px;text-decoration:none;text-transform:none;direction:ltr;color:#9095A2;text-align:center;mso-line-height-rule:exactly;mso-text-raise:2px;">The Management Team</p></td>
                    </tr></table>
                    </td></tr><tr><td><div class="t100" style="mso-line-height-rule:exactly;mso-line-height-alt:20px;line-height:20px;font-size:1px;display:block;">&nbsp;</div></td></tr></table></td>
                    </tr></table>
                    </td></tr></table></td>
                    </tr></table>
                    </td></tr></table></td></tr></table></div></body>
                    </html>
                        """);

    private String mailTemplate;

    EmailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public void setTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public String getTemplate() {
        return this.mailTemplate;
    }
}
