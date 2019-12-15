<template>
  <section>
    <transition name="fade" mode="out-in">
        <router-view :key="key"></router-view>
    </transition>
  </section>
</template>

<script>
    export default {
      name: 'main-container',
      computed: {
        key() {
          return this.$route.fullPath
        }
      }
    }
</script>

<style scoped>

</style>
