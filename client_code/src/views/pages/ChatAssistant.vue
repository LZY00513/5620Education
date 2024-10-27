<template>
  <div class="ai-chat">
    <div class="chat-history">
      <div
          v-for="(message, index) in chatHistory"
          :key="index"
          :class="['message', message.sender]"
      >
        <p>{{ message.content }}</p>
      </div>
    </div>
    <div class="input-container">
      <input
          v-model="userInput"
          @keyup.enter="sendMessage"
          placeholder="Please enter your question..."
      />
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const userInput = ref('');
const chatHistory = ref([]); // Empty initial chat history

// Predefined random responses for AI
const randomContents = [
  "Did you know? I can help you with all kinds of questions!",
  "Hello! How can I assist you today?",
  "Hi there! Feel free to ask me anything.",
  "Greetings! I'm here to answer your questions.",
  "Hello! Ready to chat?"
];

// Function to get a random message from predefined responses
function getRandomContent() {
  const randomIndex = Math.floor(Math.random() * randomContents.length);
  return randomContents[randomIndex];
}

// Function to send a random AI message
function sendRandomMessage() {
  const randomMessage = getRandomContent();
  chatHistory.value.push({
    content: randomMessage,
    sender: 'ai'
  });
}

// Display a random message only once when the component loads
onMounted(() => {
  sendRandomMessage();
});

const API_KEY = 'sk-q9f1xoMC3y2Up7WkZC7H9k3OAEHCZ6ez08qWbNmz5BDIfR79'; // Replace with your actual API key
const API_URL = 'https://zzzzapi.com/v1/chat/completions'; // Updated API endpoint

const sendMessage = async () => {
  if (userInput.value.trim() === '') return;

  // Add user message to chat history
  chatHistory.value.push({ sender: 'user', content: userInput.value });

  // Attempt to get a response from the AI API
  try {
    const response = await processMessageToChatGPT(userInput.value);
    const content = response?.data?.choices[0]?.message?.content;
    if (content) {
      chatHistory.value.push({ sender: 'ai', content: content });
    } else {
      // If there's no API response, send a random message
      sendRandomMessage();
    }
  } catch (error) {
    console.error('Error processing message:', error);
    chatHistory.value.push({
      sender: 'ai',
      content: 'Sorry, there was an error with the AI service.',
    });
  } finally {
    // Clear the input field
    userInput.value = '';
  }
};

// Function to send a message to the ChatGPT API
const processMessageToChatGPT = async (message) => {
  const apiRequestBody = {
    model: 'gpt-3.5-turbo',
    messages: [
      { role: 'system', content: "I'm a Student using ChatGPT for learning" },
      { role: 'user', content: message }
    ],
  };

  return axios.post(API_URL, apiRequestBody, {
    headers: {
      'Authorization': `Bearer ${API_KEY}`,
      'Content-Type': 'application/json',
    }
  });
};
</script>

<style scoped>
.ai-chat {
  display: flex;
  flex-direction: column;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #f4f6f9;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.chat-history {
  display: flex;
  flex-direction: column;
  max-height: 400px;
  overflow-y: auto;
  margin-bottom: 15px;
  padding: 10px;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
}

.message {
  max-width: 70%;
  padding: 8px 12px;
  margin: 5px 0;
  border-radius: 15px;
  font-size: 14px;
  line-height: 1.4;
  word-wrap: break-word;
}

.user {
  align-self: flex-end;
  background-color: #007bff;
  color: #ffffff;
  text-align: right;
  border-bottom-right-radius: 0;
}

.ai {
  align-self: flex-start;
  background-color: #e8f5e9;
  color: #28a745;
  text-align: left;
  border-bottom-left-radius: 0;
}

.input-container {
  display: flex;
  align-items: center;
  margin-top: 10px;
}

input {
  flex: 1;
  padding: 12px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-right: 10px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>
