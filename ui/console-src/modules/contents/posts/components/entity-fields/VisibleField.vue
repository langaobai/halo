<script lang="ts" setup>
import type { ListedPost, Post } from "@halo-dev/api-client";
import { coreApiClient } from "@halo-dev/api-client";
import { IconEye, IconEyeOff, Toast, VEntityField } from "@halo-dev/components";
import { useMutation, useQueryClient } from "@tanstack/vue-query";
import { useI18n } from "vue-i18n";

const queryClient = useQueryClient();
const { t } = useI18n();

withDefaults(
  defineProps<{
    post: ListedPost;
  }>(),
  {}
);

const { mutate: changeVisibleMutation } = useMutation({
  mutationFn: async (post: Post) => {
    return await coreApiClient.content.post.patchPost({
      name: post.metadata.name,
      jsonPatchInner: [
        {
          op: "add",
          path: "/spec/visible",
          value: post.spec.visible === "PRIVATE" ? "PUBLIC" : "PRIVATE",
        },
      ],
    });
  },
  retry: 3,
  onSuccess: () => {
    Toast.success(t("core.common.toast.operation_success"));
    queryClient.invalidateQueries({ queryKey: ["posts"] });
  },
  onError: () => {
    Toast.error(t("core.common.toast.operation_failed"));
  },
});
</script>

<template>
  <VEntityField>
    <template #description>
      <IconEye
        v-if="post.post.spec.visible === 'PUBLIC'"
        v-tooltip="$t('core.post.filters.visible.items.public')"
        class="cursor-pointer text-sm transition-all hover:text-blue-600"
        @click="changeVisibleMutation(post.post)"
      />
      <IconEyeOff
        v-if="post.post.spec.visible === 'PRIVATE'"
        v-tooltip="$t('core.post.filters.visible.items.private')"
        class="cursor-pointer text-sm transition-all hover:text-blue-600"
        @click="changeVisibleMutation(post.post)"
      />
    </template>
  </VEntityField>
</template>
