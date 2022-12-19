import { hasOwn } from 'element-ui/src/utils/common';

export function isVNode(node) {
  return node !== null && typeof node === 'object' && hasOwn(node, 'componentOptions');
};
