import { s as ae, r as M, d as oe, p as ne, e as ue, a as re } from "./lib/@element-plus.js";
function ie(o, F) {
  if (arguments.length === 0 || !o)
    return null;
  const u = F || "{y}-{m}-{d} {h}:{i}:{s}";
  let n;
  typeof o == "object" ? n = o : (typeof o == "string" && /^[0-9]+$/.test(o) ? o = parseInt(o) : typeof o == "string" && (o = o.replace(new RegExp(/-/gm), "/").replace("T", " ").replace(new RegExp(/\.\d{3}/gm), "")), typeof o == "number" && o.toString().length === 10 && (o = o * 1e3), n = new Date(o));
  const D = {
    y: n.getFullYear(),
    m: n.getMonth() + 1,
    d: n.getDate(),
    h: n.getHours(),
    i: n.getMinutes(),
    s: n.getSeconds(),
    a: n.getDay()
  };
  return u.replace(/{([ymdhisa])+}/g, (k, d) => {
    let f = D[d];
    return d === "a" ? ["日", "一", "二", "三", "四", "五", "六"][f] : (k.length > 0 && f < 10 && (f = "0" + f), f || 0);
  });
}
function de(o) {
  return axios.post("/api/auth/randomImg/page", o);
}
function se(o) {
  return axios.post("/api/auth/randomImg/add", o);
}
function me(o) {
  return axios.post("/api/auth/randomImg/update", o);
}
function pe(o) {
  return axios.delete("/api/auth/randomImg/del?id=" + o);
}
function ce(o) {
  return axios.get("/api/auth/randomImg/get?id=" + o);
}
function ge(o) {
  return axios.post("/api/auth/randomImg/export", o, { responseType: "blob" });
}
function fe() {
  return axios.get("/api/auth/randomImg/listAllImgAttachLibrary");
}
const s = window.Vue.resolveComponent, t = window.Vue.createVNode, a = window.Vue.withCtx, i = window.Vue.openBlock, m = window.Vue.createBlock, p = window.Vue.unref, c = window.Vue.createTextVNode, P = window.Vue.resolveDirective, V = window.Vue.withDirectives, E = window.Vue.createElementVNode, q = window.Vue.toDisplayString;
window.Vue.createCommentVNode;
const _e = window.Vue.renderList, ve = window.Vue.Fragment, j = window.Vue.createElementBlock, $ = window.Vue.isRef, we = { class: "page" }, be = { class: "search-box" }, ye = { class: "right-tool" }, Ve = { class: "table-box" }, he = { class: "dialog-footer" }, h = window.ElementPlus.ElMessage, ke = window.ElementPlus.ElMessageBox, xe = window.Vue.reactive, g = window.Vue.ref, Ce = {
  __name: "RandomImgView",
  setup(o) {
    let F = g([]);
    const u = g({
      pageNo: 1,
      pageSize: 10,
      total: 0,
      name: null,
      status: null
    }), n = g({
      id: null,
      name: null,
      remark: null,
      attachLibraryId: null,
      status: 0,
      slug: null
    }), D = xe({
      name: [{ required: !0, message: "名称不能为空", trigger: "blur" }],
      slug: [{ required: !0, message: "访问标识不能为空", trigger: "blur" }],
      attachLibraryId: [{ required: !0, message: "附件库不能为空", trigger: "blur" }],
      status: [{ required: !0, message: "是否开启不能为空", trigger: "blur" }]
    }), N = g(), k = g();
    let d = g(!1), f = g(""), z = g([]), x = g(!1), I = g(!1);
    function O() {
      k.value.validate((r) => {
        r && (n.value.id ? me(n.value).then((e) => {
          e.code === 200 ? (h.success("操作成功"), d.value = !1, C(), w()) : h.error(e.msg);
        }) : se(n.value).then((e) => {
          e.code === 200 ? (h.success("操作成功"), d.value = !1, C(), w()) : h.error(e.msg);
        }));
      });
    }
    function B() {
      fe().then((r) => {
        F.value = r.data, I.value = !1;
      });
    }
    function G() {
      I.value = !0, C(), f.value = "添加随机图", d.value = !0, B();
    }
    function H(r) {
      I.value = !0, C(), f.value = "修改随机图", d.value = !0, B(), ce(r.id).then((e) => {
        n.value = e.data, I.value = !1;
      });
    }
    function Y(r) {
      let e = Object.keys(r);
      ke.confirm("确定要删除[" + r[e[0]] + "]吗？", "提示", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        pe(r.id).then((b) => {
          b.code === 200 && b.data ? (h.success("删除成功"), w()) : h.error(b.msg);
        });
      }).catch(() => {
      });
    }
    function w() {
      x.value = !0, de(u.value).then((r) => {
        z.value = r.data.list, u.value.total = r.data.total, x.value = !1;
      });
    }
    function J() {
      u.value = {
        pageNo: 1,
        pageSize: 10,
        total: 0,
        name: null,
        status: null
      }, N.value.resetFields(), w();
    }
    function C() {
      n.value = {
        id: null,
        name: null,
        remark: null,
        attachLibraryId: null,
        status: 0,
        slug: null
      }, k.value && k.value.resetFields();
    }
    function K() {
      x.value = !0, ge(u.value).then((r) => {
        window.download.excel(r, "随机图数据.xlsx"), x.value = !1;
      }).catch((r) => {
        h.error("导出失败"), x.value = !1;
      });
    }
    return w(), (r, e) => {
      const b = s("el-input"), y = s("el-form-item"), L = s("el-option"), U = s("el-select"), _ = s("el-button"), R = s("el-form"), Q = s("el-col"), W = s("el-row"), v = s("el-table-column"), X = s("el-link"), S = s("el-tag"), Z = s("el-table"), ee = s("el-pagination"), le = s("el-dialog"), A = P("hasPermission"), T = P("loading");
      return i(), j("div", we, [
        E("div", be, [
          t(R, {
            inline: !0,
            model: u.value,
            class: "demo-form-inline",
            ref_key: "searchFormRef",
            ref: N
          }, {
            default: a(() => [
              t(y, { label: "名称" }, {
                default: a(() => [
                  t(b, {
                    modelValue: u.value.name,
                    "onUpdate:modelValue": e[0] || (e[0] = (l) => u.value.name = l),
                    placeholder: "请输入名称",
                    clearable: ""
                  }, null, 8, ["modelValue"])
                ]),
                _: 1
              }),
              t(y, { label: "是否开启" }, {
                default: a(() => [
                  t(U, {
                    modelValue: u.value.status,
                    "onUpdate:modelValue": e[1] || (e[1] = (l) => u.value.status = l),
                    placeholder: "请选择是否开启",
                    clearable: "",
                    style: { width: "200px" }
                  }, {
                    default: a(() => [
                      (i(), m(L, {
                        key: 0,
                        label: "开启",
                        value: 0
                      })),
                      (i(), m(L, {
                        key: 1,
                        label: "关闭",
                        value: 1
                      }))
                    ]),
                    _: 1
                  }, 8, ["modelValue"])
                ]),
                _: 1
              }),
              t(y, null, {
                default: a(() => [
                  V((i(), m(_, {
                    type: "primary",
                    onClick: w,
                    icon: p(ae)
                  }, {
                    default: a(() => e[11] || (e[11] = [
                      c("查询")
                    ])),
                    _: 1
                  }, 8, ["icon"])), [
                    [A, ["admin:randomImg:query"]]
                  ]),
                  t(_, {
                    icon: p(M),
                    onClick: J
                  }, {
                    default: a(() => e[12] || (e[12] = [
                      c("重置")
                    ])),
                    _: 1
                  }, 8, ["icon"]),
                  V((i(), m(_, {
                    icon: p(oe),
                    onClick: K
                  }, {
                    default: a(() => e[13] || (e[13] = [
                      c("导出")
                    ])),
                    _: 1
                  }, 8, ["icon"])), [
                    [A, ["admin:randomImg:export"]]
                  ])
                ]),
                _: 1
              })
            ]),
            _: 1
          }, 8, ["model"])
        ]),
        t(W, {
          gutter: 10,
          class: "mb8"
        }, {
          default: a(() => [
            t(Q, { span: 1.5 }, {
              default: a(() => [
                V((i(), m(_, {
                  icon: p(ne),
                  type: "primary",
                  plain: "",
                  onClick: G
                }, {
                  default: a(() => e[14] || (e[14] = [
                    c("新增")
                  ])),
                  _: 1
                }, 8, ["icon"])), [
                  [A, ["admin:randomImg:create"]]
                ])
              ]),
              _: 1
            }),
            E("div", ye, [
              t(_, {
                icon: p(M),
                circle: "",
                onClick: w
              }, null, 8, ["icon"])
            ])
          ]),
          _: 1
        }),
        E("div", Ve, [
          V((i(), m(Z, {
            data: p(z),
            style: { width: "100%", height: "100%" },
            "row-key": "id"
          }, {
            default: a(() => [
              t(v, {
                prop: "name",
                label: "名称",
                "min-width": "150"
              }),
              t(v, {
                prop: "remark",
                label: "备注",
                "min-width": "150"
              }),
              t(v, {
                prop: "slug",
                label: "访问标识",
                "min-width": "150"
              }),
              t(v, {
                prop: "slug",
                label: "访问地址",
                "min-width": "200"
              }, {
                default: a((l) => [
                  t(X, {
                    href: "/randomImg/" + l.row.slug,
                    target: "_blank"
                  }, {
                    default: a(() => [
                      c(q("/randomImg/" + l.row.slug), 1)
                    ]),
                    _: 2
                  }, 1032, ["href"])
                ]),
                _: 1
              }),
              t(v, {
                prop: "attachLibraryName",
                label: "附件库",
                "min-width": "150"
              }),
              t(v, {
                prop: "status",
                label: "是否开启",
                "min-width": "120"
              }, {
                default: a((l) => [
                  l.row.status === 0 ? (i(), m(S, {
                    key: 0,
                    type: "success"
                  }, {
                    default: a(() => e[15] || (e[15] = [
                      c("开启")
                    ])),
                    _: 1
                  })) : (i(), m(S, {
                    key: 1,
                    type: "danger"
                  }, {
                    default: a(() => e[16] || (e[16] = [
                      c("关闭")
                    ])),
                    _: 1
                  }))
                ]),
                _: 1
              }),
              t(v, {
                prop: "visitCount",
                label: "访问次数",
                "min-width": "100"
              }),
              t(v, {
                prop: "createTime",
                label: "创建时间",
                "min-width": "150"
              }, {
                default: a((l) => [
                  E("span", null, q(p(ie)(l.row.createTime)), 1)
                ]),
                _: 1
              }),
              t(v, {
                label: "操作",
                width: "240",
                fixed: "right"
              }, {
                default: a((l) => [
                  V((i(), m(_, {
                    size: "small",
                    type: "primary",
                    link: "",
                    icon: p(ue),
                    onClick: (te) => H(l.row)
                  }, {
                    default: a(() => e[17] || (e[17] = [
                      c("修改")
                    ])),
                    _: 2
                  }, 1032, ["icon", "onClick"])), [
                    [A, ["admin:randomImg:update"]]
                  ]),
                  V((i(), m(_, {
                    size: "small",
                    type: "primary",
                    link: "",
                    icon: p(re),
                    onClick: (te) => Y(l.row)
                  }, {
                    default: a(() => e[18] || (e[18] = [
                      c("删除")
                    ])),
                    _: 2
                  }, 1032, ["icon", "onClick"])), [
                    [A, ["admin:randomImg:delete"]]
                  ])
                ]),
                _: 1
              })
            ]),
            _: 1
          }, 8, ["data"])), [
            [T, p(x)]
          ]),
          t(ee, {
            "current-page": u.value.pageNo,
            "onUpdate:currentPage": e[2] || (e[2] = (l) => u.value.pageNo = l),
            "page-size": u.value.pageSize,
            "onUpdate:pageSize": e[3] || (e[3] = (l) => u.value.pageSize = l),
            "page-sizes": [10, 20, 30, 50],
            layout: "total,sizes,prev, pager, next, jumper",
            background: "",
            small: "",
            onChange: w,
            total: u.value.total
          }, null, 8, ["current-page", "page-size", "total"])
        ]),
        t(le, {
          modelValue: p(d),
          "onUpdate:modelValue": e[10] || (e[10] = (l) => $(d) ? d.value = l : d = l),
          title: p(f),
          width: "600px",
          draggable: ""
        }, {
          footer: a(() => [
            E("span", he, [
              t(_, {
                type: "primary",
                onClick: O
              }, {
                default: a(() => e[19] || (e[19] = [
                  c("确 定")
                ])),
                _: 1
              }),
              t(_, {
                onClick: e[9] || (e[9] = (l) => {
                  $(d) ? d.value = !1 : d = !1, C();
                })
              }, {
                default: a(() => e[20] || (e[20] = [
                  c("取 消")
                ])),
                _: 1
              })
            ])
          ]),
          default: a(() => [
            V((i(), m(R, {
              ref_key: "addFormRef",
              ref: k,
              model: n.value,
              "label-width": "80px",
              "status-icon": "",
              rules: D
            }, {
              default: a(() => [
                t(y, {
                  label: "名称",
                  prop: "name"
                }, {
                  default: a(() => [
                    t(b, {
                      modelValue: n.value.name,
                      "onUpdate:modelValue": e[4] || (e[4] = (l) => n.value.name = l),
                      placeholder: "请输入名称"
                    }, null, 8, ["modelValue"])
                  ]),
                  _: 1
                }),
                t(y, {
                  label: "访问标识",
                  prop: "slug"
                }, {
                  default: a(() => [
                    t(b, {
                      modelValue: n.value.slug,
                      "onUpdate:modelValue": e[5] || (e[5] = (l) => n.value.slug = l),
                      placeholder: "请输入访问标识"
                    }, null, 8, ["modelValue"])
                  ]),
                  _: 1
                }),
                t(y, {
                  label: "备注",
                  prop: "remark"
                }, {
                  default: a(() => [
                    t(b, {
                      modelValue: n.value.remark,
                      "onUpdate:modelValue": e[6] || (e[6] = (l) => n.value.remark = l),
                      placeholder: "请输入备注"
                    }, null, 8, ["modelValue"])
                  ]),
                  _: 1
                }),
                t(y, {
                  label: "附件库",
                  prop: "attachLibraryId"
                }, {
                  default: a(() => [
                    t(U, {
                      modelValue: n.value.attachLibraryId,
                      "onUpdate:modelValue": e[7] || (e[7] = (l) => n.value.attachLibraryId = l),
                      placeholder: "请选择附件库",
                      clearable: "",
                      style: { width: "200px" }
                    }, {
                      default: a(() => [
                        (i(!0), j(ve, null, _e(p(F), (l) => (i(), m(L, {
                          key: l.id,
                          label: l.name,
                          value: l.id
                        }, null, 8, ["label", "value"]))), 128))
                      ]),
                      _: 1
                    }, 8, ["modelValue"])
                  ]),
                  _: 1
                }),
                t(y, {
                  label: "是否开启",
                  prop: "status"
                }, {
                  default: a(() => [
                    t(U, {
                      modelValue: n.value.status,
                      "onUpdate:modelValue": e[8] || (e[8] = (l) => n.value.status = l),
                      placeholder: "请选择是否开启",
                      clearable: "",
                      style: { width: "200px" }
                    }, {
                      default: a(() => [
                        (i(), m(L, {
                          key: 0,
                          label: "开启",
                          value: 0
                        })),
                        (i(), m(L, {
                          key: 1,
                          label: "关闭",
                          value: 1
                        }))
                      ]),
                      _: 1
                    }, 8, ["modelValue"])
                  ]),
                  _: 1
                })
              ]),
              _: 1
            }, 8, ["model", "rules"])), [
              [T, p(I)]
            ])
          ]),
          _: 1
        }, 8, ["modelValue", "title"])
      ]);
    };
  }
};
export {
  Ce as default
};
