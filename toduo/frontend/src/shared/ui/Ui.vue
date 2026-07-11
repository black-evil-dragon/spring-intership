<script lang="ts" setup>

import {
  IUiProps,
  IUiEmits
} from './ui.types';
import useUi from './useUi';

const props = defineProps<IUiProps>();
const emit = defineEmits<IUiEmits>();

const {
} = useUi(props, emit);

</script>

<template>
  <div class="ui--shared">
    Ui.vue
  </div>
</template>

<style scoped lang="scss"></style>