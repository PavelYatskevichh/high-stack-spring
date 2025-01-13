{{- define "hs-chart.service" -}}
apiVersion: v1
kind: Service
metadata:
  name: {{ include "hs-chart.fullname" . }}-svc
  labels:
    {{- include "hs-chart.labels" . | nindent 4 }}
spec:
  type: ClusterIP
  ports:
    - name: http
      protocol: TCP
      port: {{ default 8080 (.Values.container).port }}
      targetPort: {{ default 8080 (.Values.appConfig.server).port }}
  selector:
    {{- include "hs-chart.selectorLabels" . | nindent 4 }}
{{- end }}
