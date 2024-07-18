//
//  FlowPublisher.swift
//  iosApp
//
//  Created by Baldomero Aguila on 18-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

struct FlowPublisher<T: Any>: Publisher {
    public typealias Output = T
    public typealias Failure = Never
    private let flow: Kotlinx_coroutines_coreFlow

    public init(flow: Kotlinx_coroutines_coreFlow) {
        self.flow = flow
    }

    public func receive<S: Subscriber>(subscriber: S) where S.Input == T, S.Failure == Failure {
        subscriber.receive(subscription: FlowSubscription(flow: flow, subscriber: subscriber))
    }

    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == T, S.Failure == Failure {
        private var subscriber: S?
        private var job: Kotlinx_coroutines_coreJob?
        private let flow: Kotlinx_coroutines_coreFlow
        init(flow: Kotlinx_coroutines_coreFlow, subscriber: S) {
            self.flow = flow
            self.subscriber = subscriber
            job = SubscribeKt.subscribe(
                flow,
                onEach: { item in if let item = item as? T { _ = subscriber.receive(item) }},
                onComplete: { subscriber.receive(completion: .finished) },
                onThrow: { error in debugPrint(error) }
            )
        }

        func cancel() {
            subscriber = nil
            job?.cancel(cause: nil)
        }
    }
}
