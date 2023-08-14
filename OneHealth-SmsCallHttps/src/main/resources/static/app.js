document.addEventListener('DOMContentLoaded', () => {
    const smsForm = document.getElementById('smsForm');
    const voiceCallForm = document.getElementById('voiceCallForm');

    smsForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const to = event.target.to.value;
        const message = event.target.message.value;
        await sendSms(to, message);
    });

    voiceCallForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const identity = event.target.identity.value;
        await generateVoiceToken(identity);
    });
});

async function sendSms(to, message) {
    try {
        const response = await fetch('/send-sms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ to, message }),
        });

        if (response.ok) {
            alert('SMS sent successfully');
        } else {
            alert('Failed to send SMS');
        }
    } catch (error) {
        console.error('Error sending SMS:', error);
    }
}

async function generateVoiceToken(identity) {
    try {
        const response = await fetch('/generate-voice-token', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ identity }),
        });

        if (response.ok) {
            const token = await response.text();
            // Use the generated token to initiate a voice call
            // Implement the voice call logic here
        } else {
            alert('Failed to generate voice call token');
        }
    } catch (error) {
        console.error('Error generating voice call token:', error);
    }
}
