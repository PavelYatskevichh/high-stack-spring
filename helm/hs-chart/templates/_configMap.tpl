{{- define "hs-chart.configMap" -}}
apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ include "hs-chart.fullname" . }}-config
  labels:
    {{- include "hs-chart.labels" . | nindent 4 }}
data:
  SPRING_THREADS_VIRTUAL_ENABLED: {{ default "false" ((.Values.appConfig.spring).threads.virtual).enabled | quote }}
  SERVER_PORT: {{ default 8080 .Values.appConfig.server.port | quote }}
  {{- if ((.Values.appConfig.logging).level.com).yatskevich }}
  LOGGING_LEVEL_COM_YATSKEVICH: {{ .Values.appConfig.logging.level.com.yatskevich | quote }}
  {{- end }}
  {{- if (.Values.appConfig.kafka).bootstrapServersPattern }}
  KAFKA_BOOTSTRAPSERVERS: {{ printf .Values.appConfig.kafka.bootstrapServersPattern .Release.Namespace | quote }}
  {{- end }}
{{- end }}
