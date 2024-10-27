<template>
  <div style="width: 100%">
    <swiper v-if="data && data.length" class="swiper" :loop="loop" :modules="swiperModules" :navigation="navigation"
            :pagination="paginationEnd" :scrollbar="scrollbarEnd" :space-between="spaceBetween"
            :auto-height="autoHeight" :slides-per-view="slidesPerView" :centered-slides="centeredSlides"
            :free-mode="freeMode" :effect="effectEnd" :coverflow-effect="coverflowEffect" :cube-effect="cubeEffect"
            :creative-effect="creativeEffect" :direction="direction" :autoplay="autoplayEnd" :grid="gridEnd">
      <swiper-slide class="slide" v-for="(item, index) in data" :key="index">
        <el-image v-if="type == 1" style="width: 100%;" :src="item.value ? $config.url + item.value : ''"></el-image>
        <el-image v-if="type == 2" style="width: 100%;" :src="item ? $config.url + item : ''"></el-image>
        <slot :row="item" :index="index" v-if="type == 3"></slot>
      </swiper-slide>
    </swiper>
  </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import {
  Navigation,
  Pagination,
  Scrollbar,
  FreeMode,
  EffectFade,
  EffectCube,
  EffectCreative,
  EffectFlip,
  EffectCoverflow,
  Autoplay,
  Grid,
} from 'swiper';
import { ref, toRefs } from 'vue';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';
import 'swiper/css/free-mode';
import 'swiper/css/effect-fade';
import 'swiper/css/effect-coverflow';
import 'swiper/css/effect-cube';
import 'swiper/css/effect-flip';
import 'swiper/css/effect-creative';
import 'swiper/css/grid';

const props = defineProps({
  type: {
    type: Number,
    default: 1,
  },
  // Loop
  loop: {
    type: Boolean,
    default: false,
  },
  // Navigation buttons
  navigation: {
    type: Boolean,
    default: true,
  },
  // Pagination dots
  pagination: {
    type: Boolean,
    default: true,
  },
  // Scrollbar
  scrollbar: {
    type: Boolean,
    default: false,
  },
  // Number of slides visible
  slidesPerView: {
    type: Number,
    default: 3,
  },
  // Space between slides
  spaceBetween: {
    type: Number,
    default: 30,
  },
  // Auto height adjustment
  autoHeight: {
    type: Boolean,
    default: false,
  },
  // Centered slides (showcase mode, not compatible with loop)
  centeredSlides: {
    type: Boolean,
    default: false,
  },
  // Free mode (non-sliding)
  freeMode: {
    type: Boolean,
    default: false,
  },
  // Pagination type: 1. default, 2. dynamic bullets, 3. progress bar, 4. fraction, 5. numbered circles
  paginationType: {
    type: Number,
    default: 1,
  },
  // Slide effect: 0. default, 1. fade, 2. 3D coverflow, 3. 3D cube, 4. 3D flip, 5. card stack, 6. scale transition, 7. replace, 8. flip vertically, 9. rotate, 10. book flip
  effectType: {
    type: Number,
    default: 0,
  },
  // Slide data
  data: {
    type: Array,
    default: [],
  },
  // Slide direction: 'horizontal' or 'vertical'
  direction: {
    type: String,
    default: 'horizontal',
  },
  // Autoplay
  autoplay: {
    type: Boolean,
    default: false,
  },
  slidesPerColumn: {
    type: Number,
    default: 1,
  },
});

const swiperModules = ref([]);
// Different pagination types
const paginationEnd = ref(null);
const bulletRenderer = (index, className) => {
  const _className = `${className} swiper-pagination-bullet-custom`;
  return `<span class="${_className}">${index + 1}</span>`;
};
// Scrollbar
const scrollbarEnd = ref(false);
const { type, loop, navigation, pagination, scrollbar, paginationType, spaceBetween, autoHeight, slidesPerView, centeredSlides, freeMode, effectType, data, direction, autoplay, slidesPerColumn } = toRefs(props);

const init = () => {
  navigationChange();
  paginationChange();
  scrollbarChange();
  freeModeChange();
  effectTypeChange();
  autoplayChange();
  gridChange();
};

const navigationChange = () => {
  if (navigation.value) {
    swiperModules.value.push(Navigation);
  }
};

const paginationChange = () => {
  if (pagination.value) {
    swiperModules.value.push(Pagination);
    if (paginationType.value == 1) {
      paginationEnd.value = true;
    } else if (paginationType.value == 2) {
      paginationEnd.value = {
        clickable: true,
        dynamicBullets: true,
      };
    } else if (paginationType.value == 3) {
      paginationEnd.value = {
        type: 'progressbar',
      };
    } else if (paginationType.value == 4) {
      paginationEnd.value = {
        type: 'fraction',
      };
    } else if (paginationType.value == 5) {
      paginationEnd.value = {
        clickable: true,
        renderBullet: bulletRenderer,
      };
    }
  } else {
    paginationEnd.value = false;
  }
};

const scrollbarChange = () => {
  if (scrollbar.value) {
    swiperModules.value.push(Scrollbar);
    scrollbarEnd.value = {
      hide: true,
    };
  }
};

const freeModeChange = () => {
  if (freeMode.value) {
    swiperModules.value.push(FreeMode);
  }
};

const effectEnd = ref(false);
const coverflowEffect = ref(false);
const cubeEffect = ref(false);
const creativeEffect = ref(false);

const effectTypeChange = () => {
  if (effectType.value == 1) {
    effectEnd.value = 'fade';
    swiperModules.value.push(EffectFade);
  } else if (effectType.value == 2) {
    effectEnd.value = 'coverflow';
    swiperModules.value.push(EffectCoverflow);
    coverflowEffect.value = {
      rotate: 50,
      stretch: 0,
      depth: 100,
      modifier: 1,
      slideShadows: true,
    };
  } else if (effectType.value == 3) {
    effectEnd.value = 'cube';
    swiperModules.value.push(EffectCube);
    cubeEffect.value = {
      shadow: true,
      slideShadows: true,
      shadowOffset: 20,
      shadowScale: 0.94,
    };
  } else if (effectType.value == 4) {
    effectEnd.value = 'flip';
    swiperModules.value.push(EffectFlip);
  } else if (effectType.value == 5) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        translate: [0, 0, -400],
      },
      next: {
        translate: ['100%', 0, 0],
      },
    };
  } else if (effectType.value == 6) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        translate: ['-120%', 0, -500],
      },
      next: {
        shadow: true,
        translate: ['120%', 0, -500],
      },
    };
  } else if (effectType.value == 7) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        translate: ['-20%', 0, -1],
      },
      next: {
        translate: ['100%', 0, 0],
      },
    };
  } else if (effectType.value == 8) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        translate: [0, 0, -800],
        rotate: [180, 0, 0],
      },
      next: {
        shadow: true,
        translate: [0, 0, -800],
        rotate: [-180, 0, 0],
      },
    };
  } else if (effectType.value == 9) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        translate: ['-125%', 0, -800],
        rotate: [0, 0, -90],
      },
      next: {
        shadow: true,
        translate: ['125%', 0, -800],
        rotate: [0, 0, 90],
      },
    };
  } else if (effectType.value == 10) {
    effectEnd.value = 'creative';
    swiperModules.value.push(EffectCreative);
    creativeEffect.value = {
      prev: {
        shadow: true,
        origin: 'left center',
        translate: ['-5%', 0, -200],
        rotate: [0, 100, 0],
      },
      next: {
        origin: 'right center',
        translate: ['5%', 0, -200],
        rotate: [0, -100, 0],
      },
    };
  }
};

const autoplayEnd = ref(false);
const autoplayChange = () => {
  if (autoplay.value) {
    autoplayEnd.value = {
      delay: 2500,
      disableOnInteraction: false,
    };
    swiperModules.value.push(Autoplay);
  }
};

const gridEnd = ref({
  rows: 1,
  fill: 'row',
});

const gridChange = () => {
  gridEnd.value.rows = slidesPerColumn.value;
  swiperModules.value.push(Grid);
};

init();
</script>

<style lang="scss" scoped>
.swiper {
  width: 100%;

  .slide {
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>
