import DefaultTheme from 'vitepress/theme'

import mediumZoom from 'medium-zoom';
import giscusTalk from 'vitepress-plugin-comment-with-giscus';
import { useData, useRoute } from 'vitepress';
import { onMounted, watch, nextTick, h } from 'vue';
import { NolebaseEnhancedReadabilitiesMenu, NolebaseEnhancedReadabilitiesScreenMenu } from '@nolebase/vitepress-plugin-enhanced-readabilities/client'

import './style/index.css'
import '@nolebase/vitepress-plugin-enhanced-readabilities/client/style.css'

export default {
  extends: DefaultTheme,

  /* Nólëbase Integrations */
  Layout: () => {
    return h(DefaultTheme.Layout, null, {
      'nav-bar-content-after': () => h(NolebaseEnhancedReadabilitiesMenu),
      'nav-screen-content-after': () => h(NolebaseEnhancedReadabilitiesScreenMenu),
    })
  },


  setup() {
    const { frontmatter } = useData();
    const route = useRoute();

    /* giscus */
    giscusTalk({
      repo: 'compose-miuix-ui/miuix-giscus',
      repoId: 'R_kgDOQo99Eg',
      category: 'General',
      categoryId: 'DIC_kwDOQo99Es4Cz0CR',
      inputPosition: 'bottom',
      locales: {
        'zh_CN': 'zh-CN',
        'en_US': 'en'
      },
      homePageShowComment: false,
    },
      {
        frontmatter, route
      },
      true
    );

    /* Medium Zoom */
    const initZoom = () => {
      mediumZoom('.main img', { background: 'var(--vp-c-bg)' });
    };
    onMounted(() => {
      initZoom();
    });
    watch(
      () => route.path,
      () => nextTick(() => initZoom())
    );
  },
}