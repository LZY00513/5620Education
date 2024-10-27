<template>
  <div>
    <p v-if="msTime.show" style="margin: 0;">
      <span v-if="tipShow">{{ tipText }}:</span>
      <span v-if="!tipShow">{{ tipTextEnd }}:</span>
      <span v-if="msTime.day > 0"><span>{{ msTime.day }}</span><i>{{ dayTxt }}</i></span>
      <span>{{ msTime.hour }}</span><i>{{ hourTxt }}</i>
      <span>{{ msTime.minutes }}</span><i>{{ minutesTxt }}</i>
      <span>{{ msTime.seconds }}</span><i>{{ secondsTxt }}</i>
    </p>
    <p v-if="!msTime.show">{{ endText }}</p>
  </div>
</template>

<script setup>
import { ref, toRefs, watch } from 'vue';

const emit = defineEmits(['end_callback', 'start_callback']);
const tipShow = ref(true);
const msTime = ref({
  show: false, // Countdown status
  day: '', // Days
  hour: '', // Hours
  minutes: '', // Minutes
  seconds: '', // Seconds
});
const star = ref('');
const end = ref('');
const current = ref('');

// Props definition
const props = defineProps({
  // Text displayed before the start
  tipText: {
    type: String,
    default: 'Time until start',
  },
  // Text displayed before the end
  tipTextEnd: {
    type: String,
    default: 'Time until end',
  },
  // Timer ID
  id: {
    type: String,
    default: '1',
  },
  // Current time
  currentTime: {
    type: Number,
  },
  // Event start time
  startTime: {
    type: Number,
  },
  // Event end time
  endTime: {
    type: Number,
  },
  // Text displayed when the countdown ends
  endText: {
    type: String,
    default: 'Ended',
  },
  // Custom display text for days
  dayTxt: {
    type: String,
    default: ':',
  },
  // Custom display text for hours
  hourTxt: {
    type: String,
    default: ':',
  },
  // Custom display text for minutes
  minutesTxt: {
    type: String,
    default: ':',
  },
  // Custom display text for seconds
  secondsTxt: {
    type: String,
    default: ':',
  },
  // Stopwatch countdown (not yet completed)
  secondsFixed: {
    type: Boolean,
    default: false,
  },
});

const {
  tipText,
  tipTextEnd,
  id,
  currentTime,
  startTime,
  endTime,
  endText,
  dayTxt,
  hourTxt,
  minutesTxt,
  secondsTxt,
  secondsFixed,
} = toRefs(props);

// Watcher for currentTime
watch(currentTime, () => {
  gogogo();
});

const gogogo = () => {
  // Check if the time is in seconds or milliseconds
  startTime.value.toString().length == 10 ? (star.value = startTime.value * 1000) : (star.value = startTime.value);
  endTime.value.toString().length == 10 ? (end.value = endTime.value * 1000) : (end.value = endTime.value);

  if (currentTime.value) {
    currentTime.value.toString().length == 10
        ? (current.value = currentTime.value * 1000)
        : (current.value = currentTime.value);
  } else {
    current.value = new Date().getTime();
  }

  if (end.value < current.value) {
    // The event has already ended
    msTime.value.show = false;
    end_message();
  } else if (current.value < star.value) {
    // The event has not started yet
    tipShow.value = true;
    setTimeout(() => {
      runTime(star.value, current.value, start_message.value);
    }, 1);
  } else if ((end.value > current.value && star.value < current.value) || star.value == current.value) {
    // The event has started and is in progress
    tipShow.value = false;
    msTime.value.show = true;
    emit('start_callback', msTime.value.show);
    setTimeout(() => {
      runTime(end.value, current.value, end_message.value, true);
    }, 1);
  }
};

const runTime = (startTime, endTime, callFun, type) => {
  let msTimes = msTime.value;
  let timeDistance = startTime - endTime;

  if (timeDistance > 0) {
    msTime.value.show = true;
    msTimes.day = Math.floor(timeDistance / 86400000);
    timeDistance -= msTimes.day * 86400000;
    msTimes.hour = Math.floor(timeDistance / 3600000);
    timeDistance -= msTimes.hour * 3600000;
    msTimes.minutes = Math.floor(timeDistance / 60000);
    timeDistance -= msTimes.minutes * 60000;
    msTimes.seconds = Math.floor(timeDistance / 1000).toFixed(0);
    timeDistance -= msTimes.seconds * 1000;

    if (msTimes.hour < 10) {
      msTimes.hour = '0' + msTimes.hour;
    }
    if (msTimes.minutes < 10) {
      msTimes.minutes = '0' + msTimes.minutes;
    }
    if (msTimes.seconds < 10) {
      msTimes.seconds = '0' + msTimes.seconds;
    }

    let _s = Date.now();
    let _e = Date.now();
    let diffPerFunc = _e - _s;

    setTimeout(() => {
      if (type) {
        runTime(end.value, (endTime += 1000), callFun, true);
      } else {
        runTime(star.value, (endTime += 1000), callFun);
      }
    }, 1000 - diffPerFunc);
  } else {
    callFun();
  }
};

const start_message = () => {
  tipShow.value = false;
  emit('start_callback', msTime.value.show);
  setTimeout(() => {
    runTime(end.value, star.value, end_message.value, true);
  }, 1);
};

const end_message = () => {
  msTime.value.show = false;
  if (currentTime.value <= 0) {
    return;
  }
  emit('end_callback', msTime.value.show);
};

gogogo();
</script>

<style></style>
