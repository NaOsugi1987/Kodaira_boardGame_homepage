function send_email(){
  	var google = require('googleapis');
    var gmailClass = google.gmail('v1');

    var CLIENT_ID = '1050649807688-hpcfc60ttramflanrrklkljc29uimhmn.apps.googleusercontent.com',
    CLIENT_SECRET = 'nG6uqFZ0OZF31DbcXYNv59Cx',
    REDIRECT_URL = 'urn:ietf:wg:oauth:2.0:oob',

    var auth = new googleAuth();
    var oauth2Client = new auth.OAuth2(clientId, clientSecret, redirectUrl);


    var email_lines = [];

    email_lines.push("From: \"Naoya Osugi\" <7842wombat3994@gmail.com>");
    email_lines.push("To: naosugi@r.recruit.co.jp");
    email_lines.push('Content-type: text/html;charset=utf-8');
    email_lines.push('MIME-Version: 1.0');
    email_lines.push("Subject: test");
    email_lines.push("");
    email_lines.push("And the body text goes here");
    email_lines.push("本文ですよ");

    var email =email_lines.join("\r\n").trim();

    var base64EncodedEmail = new Buffer(email).toString('base64');

    gmailClass.users.messages.send({
    auth: oauth2Client,
    userId: "1050649807688-hpcfc60ttramflanrrklkljc29uimhmn.apps.googleusercontent.com",
    resource: 
    {
         raw: base64EncodedEmail
    }
  },
    function(err, results){
        console.log(err);
        console.log(results);
    });
};

send_email();